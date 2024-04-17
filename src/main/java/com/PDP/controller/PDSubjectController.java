package com.PDP.controller;

import com.PDP.model.PDSubject;
import com.PDP.service.PDSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subject")
public class PDSubjectController {
    @Autowired
    private final PDSubjectService pdSubjectService;
    public Iterable<PDSubject> getAll(){
        return pdSubjectService.getAll();
    }
    @PostMapping("/editMany")
    public void editMany(Iterable <PDSubject> pdSubjects){
        for(PDSubject sub:pdSubjects){
            if (sub.getId()==null){
                PDSubject pdSubject = new PDSubject();
                sub.setId(pdSubject.getId());
            }
            pdSubjectService.edit(sub, sub.getId());
        }
    }
}
