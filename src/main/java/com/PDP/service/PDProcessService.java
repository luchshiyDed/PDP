package com.PDP.service;

import com.PDP.model.PDProcess;
import com.PDP.model.PDProcessAction;
import com.PDP.model.PDSubject;
import com.PDP.model.PDTarget;
import com.PDP.repository.PDProcessRepository;
import com.PDP.security.user.UserEntity;
import com.PDP.util.reports.ISPDNReport;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class PDProcessService {
    @Autowired
    private final PDProcessRepository repository;
    @Autowired
    private final PDTargetService pdTargetService;
    @Autowired
    private final PDSubjectService pdSubjectService;

    @Autowired
    private final EmployeeService employeeService;
    @Autowired
    private final PDRegDocService pdRegDocService;
    @Autowired
    private final PDProcessTypeService pdProcessTypeService;

    @Autowired
    private final PDStorageService pdStorageService;

    @Autowired
    private final PDDocumentService pdDocumentService;

    @Autowired
    private final PDTypeService pdTypeService;

    @Autowired
    private final ISPDNService ispdnService;

    @Autowired
    private final ICOPDService icopdService;

    @Autowired
    private final PDProcessActionService pdProcessActionService;
    private void createMissingEntities(PDProcess pdProcess) {
        if (pdProcess.getPdTargets() != null) {
            ArrayList<PDTarget> newPdtargets = new ArrayList<>(pdProcess.getPdTargets().size());
            for (PDTarget pdt : pdProcess.getPdTargets()) {
                newPdtargets.add(pdTargetService.findByNameOrCreate(pdt));
            }
            pdProcess.setPdTargets(newPdtargets);
        }
        if (pdProcess.getPdSubjects() != null) {
            ArrayList<PDSubject> newArr = new ArrayList<>(pdProcess.getPdSubjects().size());
            for (PDSubject pds : pdProcess.getPdSubjects()) {
                newArr.add(pdSubjectService.findByNameOrCreate(pds));
            }
            pdProcess.setPdSubjects(newArr);
        }
        if (pdProcess.getPdProcessActions() != null) {
            ArrayList<PDProcessAction> newArr = new ArrayList<>(pdProcess.getPdProcessActions().size());
            for (PDProcessAction pda : pdProcess.getPdProcessActions()) {
                newArr.add(pdProcessActionService.findByNameOrCreate(pda));
            }
            pdProcess.setPdProcessActions(newArr);
        }
        pdProcess.setPdRegDoc(pdRegDocService.findByNameOrCreate(pdProcess.getPdRegDoc()));
        pdProcess.setEmployee(employeeService.findByNameOrCreate(pdProcess.getEmployee()));
        pdProcess.setPdProcessType(pdProcessTypeService.findByNameOrCreate(pdProcess.getPdProcessType()));
        pdProcess.setPdStorage(pdStorageService.findByNameOrCreate(pdProcess.getPdStorage()));
        pdProcess.setPdDocument(pdDocumentService.findByNameOrCreate(pdProcess.getPdDocument()));
        pdProcess.setPdType(pdTypeService.findByNameOrCreate(pdProcess.getPdType()));
        pdProcess.setIspdn(ispdnService.findByNameOrCreate(pdProcess.getIspdn()));
        pdProcess.setIcopd(icopdService.findByNameOrCreate(pdProcess.getIcopd()));


    }

    public Iterable<PDProcess> getAllPDProcesses(Authentication auth) {
        UserEntity user= (UserEntity) auth.getPrincipal();
        if(user.getAllSubdivisions())
            return repository.findAll();

        return repository.findAll().stream().filter(pdProcess -> {
                return pdProcess.getIcopd().getSubdivision().getName().equals(user.getSubdivision());
            }).toList();
    }

    public HttpStatus createIfNotExists(PDProcess pdProcess) {
        if (pdProcess.getId() != null)
            if (repository.existsById(pdProcess.getId())) {
                return HttpStatus.OK;
            }
        createMissingEntities(pdProcess);
        repository.saveAndFlush(pdProcess);
        return HttpStatus.CREATED;
    }

    public HttpStatus editPDProcess(PDProcess pdProcess, Long id) {
        if(id==null){
            PDProcess pd=new PDProcess();
            pdProcess.setId(pd.getId());
            createMissingEntities(pdProcess);
            System.out.println(pdProcess);
            repository.saveAndFlush(pdProcess);

            return HttpStatus.CREATED;
        }
        Optional<PDProcess> old = repository.findById(id);
        createMissingEntities(pdProcess);
        if (old.isEmpty()) {
            repository.saveAndFlush(pdProcess);
            return HttpStatus.CREATED;
        }

        pdProcess.setId(old.get().getId());
        repository.saveAndFlush(pdProcess);
        return HttpStatus.OK;

    }

    public HttpStatus deleteById(Long id) {
        repository.deleteById(id);
        return HttpStatus.OK;
    }

    public HashMap<Long, ISPDNReport> getISPDNsInfo(Authentication auth) {
        HashMap<Long, ISPDNReport> reports = new HashMap<>();
        for (PDProcess pdProcess : getAllPDProcesses(auth)) {
            ISPDNReport tmp = reports.get(pdProcess.getIspdn().getId());
            if (tmp == null) {
                tmp = new ISPDNReport();
            }
            tmp.setIspdnLocation(pdProcess.getIspdn().getLocation());
            tmp.setIspdnTransBorder(pdProcess.getIspdn().getOverBorder());
            tmp.getPdSubjects().addAll(pdProcess.getPdSubjects());
            tmp.getPdProcessTypes().add(pdProcess.getPdProcessType());
            tmp.getPdTypes().add(pdProcess.getPdType());
            tmp.getPdProcessActions().addAll(pdProcess.getPdProcessActions());
            reports.put(pdProcess.getIspdn().getId(), tmp);

        }
        return reports;

    }
}
