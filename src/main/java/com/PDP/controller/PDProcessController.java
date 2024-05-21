package com.PDP.controller;

import com.PDP.model.Employee;
import com.PDP.model.PDProcess;
import com.PDP.service.PDProcessService;
import com.PDP.util.reports.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    @DeleteMapping("/delete/{id}")
    public void deletePDProcess(Authentication authentication,@PathVariable Long id) {
         pdProcessService.delete(authentication,id);
    }


    @GetMapping("/reports")
    public List<ReportInfo> getAllReports(){
        ArrayList<ReportInfo> reportInfos=new ArrayList<>();
        reportInfos.add(ICOPDReport.getReportInfo());
        reportInfos.add(AWPReport.getReportInfo());
        reportInfos.add(EmployeeReport.getReportInfo());
        reportInfos.add(PdDocumentReport.getReportInfo());
        return reportInfos;
    }
    @PostMapping("/reports/awp")
    public AWPReport getAWPReport(Authentication authentication, @RequestBody String subdivision) {
        return pdProcessService.getAWPReport(subdivision.replaceAll("\"",""));
    }
    @PostMapping("/reports/icopd")
    public ICOPDReport getICOPD(@RequestBody String subdivision){
        return pdProcessService.getICOPDReport(subdivision.replaceAll("\"",""));
    }
    @PostMapping("/reports/employee")
    public EmployeeReport getEmployeeReport(){
        return pdProcessService.getEmployeeReport();
    }
    @PostMapping("/reports/document")
    public PdDocumentReport getDocumentReport(){
        return pdProcessService.getDocumentReport();
    }
    @PostMapping("/editMany")
    public void editPDPProcesses(Authentication auth,@RequestBody List<PDProcess> pdProcessList){
        for(PDProcess pd:pdProcessList){
            if(pd.getId()==null){
                pdProcessService.createIfNotExists(pd);
            }
            else
                pdProcessService.editPDProcess(auth,pd, pd.getId());
        }
    }
}
