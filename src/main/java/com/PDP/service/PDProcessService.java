package com.PDP.service;

import com.PDP.DTO.AWPAndEmployeeDTO;
import com.PDP.DTO.PDProcessDTO;
import com.PDP.model.*;
import com.PDP.repository.PDProcessRepository;
import com.PDP.security.user.UserEntity;
import com.PDP.util.reports.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class PDProcessService extends AuthCheckingService<PDProcess> {
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

    @Autowired
    private final DeleteConditionService deleteConditionService;

    public PDProcessService(PDProcessRepository repository1, PDTargetService pdTargetService, PDSubjectService pdSubjectService, EmployeeService employeeService, PDRegDocService pdRegDocService, PDProcessTypeService pdProcessTypeService, PDStorageService pdStorageService, PDDocumentService pdDocumentService, PDTypeService pdTypeService, ISPDNService ispdnService, ICOPDService icopdService, PDProcessActionService pdProcessActionService, DeleteConditionService deleteConditionService) {
        super(repository1);
        this.pdTargetService = pdTargetService;
        this.pdSubjectService = pdSubjectService;
        this.employeeService = employeeService;
        this.pdRegDocService = pdRegDocService;
        this.pdProcessTypeService = pdProcessTypeService;
        this.pdStorageService = pdStorageService;
        this.pdDocumentService = pdDocumentService;
        this.pdTypeService = pdTypeService;
        this.ispdnService = ispdnService;
        this.icopdService = icopdService;
        this.pdProcessActionService = pdProcessActionService;
        this.deleteConditionService = deleteConditionService;
    }

    private void createMissingEntities(PDProcess pdProcess) {
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
        if(pdProcess.getPdType()!=null){
            ArrayList<PDType> newArr = new ArrayList<>(pdProcess.getPdType().size());
            for (PDType pda : pdProcess.getPdType()) {
                newArr.add(pdTypeService.findByNameOrCreate(pda));
            }
            pdProcess.setPdType(newArr);
        }
        pdProcess.setPdTargets(pdTargetService.findByNameOrCreate(pdProcess.getPdTargets()));
        pdProcess.setPdRegDoc(pdRegDocService.findByNameOrCreate(pdProcess.getPdRegDoc()));
        pdProcess.setEmployee(employeeService.findByNameOrCreate(pdProcess.getEmployee()));
        pdProcess.setPdProcessType(pdProcessTypeService.findByNameOrCreate(pdProcess.getPdProcessType()));
        pdProcess.setPdStorage(pdStorageService.findByNameOrCreate(pdProcess.getPdStorage()));
        pdProcess.setPdDocument(pdDocumentService.findByNameOrCreate(pdProcess.getPdDocument()));
        pdProcess.setPdProcessPlace(pdStorageService.findByNameOrCreate(pdProcess.getPdProcessPlace()));
        //pdProcess.setIspdn(ispdnService.findByNameOrCreate(pdProcess.getIspdn()));
        pdProcess.setDeleteCondition(deleteConditionService.findByNameOrCreate(pdProcess.getDeleteCondition()));


    }

    public Iterable<PDProcess> getAllPDProcesses(Authentication auth) {
        UserEntity user = (UserEntity) auth.getPrincipal();
        if (user.getAllSubdivisions())
            return repository.findAll();

        return repository.findAll().stream().filter(pdProcess -> {
            return pdProcess.getSubdivisionName().equals(user.getSubdivision());
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

    public HttpStatus editPDProcess(Authentication auth, PDProcess pdProcess, Long id) {
        UserEntity user = (UserEntity) auth.getPrincipal();
        Optional<PDProcess> old = repository.findById(id);
        createMissingEntities(pdProcess);
        if (old.isEmpty()) {
            if (checkAuth(user, pdProcess)) {
                repository.saveAndFlush(pdProcess);
                return HttpStatus.CREATED;
            }
            return HttpStatus.FORBIDDEN;
        }
        if (checkAuth(user, old.get())) {
            pdProcess.setId(old.get().getId());
            repository.saveAndFlush(pdProcess);
            return HttpStatus.OK;
        }
        return HttpStatus.FORBIDDEN;

    }


//    public HashMap<Long, ISPDNReport> getISPDNsInfo(Authentication auth) {
//        HashMap<Long, ISPDNReport> reports = new HashMap<>();
//        for (PDProcess pdProcess : getAllPDProcesses(auth)) {
//            ISPDNReport tmp = reports.get(pdProcess.getIspdn().getId());
//            if (tmp == null) {
//                tmp = new ISPDNReport();
//            }
//            tmp.setIspdnLocation(pdProcess.getIspdn().getLocation());
//            tmp.setIspdnTransBorder(pdProcess.getIspdn().getOverBorder());
//            tmp.getPdSubjects().addAll(pdProcess.getPdSubjects());
//            tmp.getPdProcessTypes().add(pdProcess.getPdProcessType());
//            //tmp.getPdType().add(pdProcess.getPdType());
//            tmp.getPdProcessActions().addAll(pdProcess.getPdProcessActions());
//            reports.put(pdProcess.getIspdn().getId(), tmp);
//
//        }
//        return reports;
//
//    }

    public ICOPDReport getICOPDReport(String subdivision) {
        List<PDProcess> pdProcesses= repository.findAll().stream().filter(pdProcess -> pdProcess.getSubdivisionName().equals(subdivision)).toList();
        List<AWPAndEmployeeDTO> awpAndEmployeeDTOS= new ArrayList<>();
        //TODO: сделать метод в репозитории поиск по подразделению
        for(Employee e:employeeService.getAll().stream().filter(employee -> employee.getSubdivisionName().equals(subdivision)).toList()){
            awpAndEmployeeDTOS.add(new AWPAndEmployeeDTO(e));
        }
        return new ICOPDReport(subdivision,pdProcesses,awpAndEmployeeDTOS);
    }

    public AWPReport getAWPReport(String subdivision) {
        List<AWPAndEmployeeDTO> awpAndEmployeeDTOS= new ArrayList<>();
        //TODO: сделать метод в репозитории поиск по подразделению
        for(Employee e:employeeService.getAll().stream().filter(employee -> employee.getSubdivisionName().equals(subdivision)).toList()){
            awpAndEmployeeDTOS.add(new AWPAndEmployeeDTO(e));
        }
        return new AWPReport(subdivision,awpAndEmployeeDTOS);
    }

    public EmployeeReport getEmployeeReport() {
        List<AWPAndEmployeeDTO> awpAndEmployeeDTOS= new ArrayList<>();
        for(Employee e:employeeService.getAll()){
            awpAndEmployeeDTOS.add(new AWPAndEmployeeDTO(e));
        }
        return new EmployeeReport(awpAndEmployeeDTOS);
    }

    public PdDocumentReport getDocumentReport() {
        List<PDProcessDTO> pdProcessDTOS=new ArrayList<>();
        for(PDProcess pdProcess: repository.findAll()){
            pdProcessDTOS.add(new PDProcessDTO(pdProcess));
        }
        return  new PdDocumentReport(pdProcessDTOS);
    }
}
