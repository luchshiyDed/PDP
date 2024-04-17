package com.PDP.controller;

import com.PDP.model.PDProcess;
import com.PDP.service.PDProcessService;
import com.PDP.util.reports.ISPDNReport;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/PDP")
public class PDProcessController {
    @Autowired
    private final PDProcessService pdProcessService;

    @GetMapping
    public Iterable<PDProcess> getAllPDProcesses(Authentication auth) {
        return pdProcessService.getAllPDProcesses(auth);
    }

    @PostMapping("/create")
    public void createPDProcess(@RequestBody PDProcess pdProcess) {
         pdProcessService.createIfNotExists(pdProcess);
    }

    @PostMapping("/edit/{id}")
    public void editPDProcess(@PathVariable Long id, @RequestBody PDProcess pdProcess) {
         pdProcessService.editPDProcess(pdProcess, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePDProcess(@PathVariable Long id) {
         pdProcessService.deleteById(id);
    }

    @GetMapping("/reports/AllISPDNReport")
    public HashMap<Long,ISPDNReport> getISPDNReports(Authentication authentication) {
        return pdProcessService.getISPDNsInfo(authentication);
    }


    @PostMapping("/editMany")
    public void editPDPProcesses(Authentication auth,@RequestBody List<PDProcess> pdProcessList){
        for(PDProcess pd:pdProcessList){
            if(pd.getId()==null){
                pdProcessService.createIfNotExists(pd);
                return;
            }
            pdProcessService.editPDProcess(pd, pd.getId());
        }
    }
}
