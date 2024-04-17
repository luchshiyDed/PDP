package com.PDP.controller;

import com.PDP.model.PDType;
import com.PDP.service.PDTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/type")
public class PDTypeController {
    @Autowired
    private final PDTypeService pdTypeService;
    @GetMapping
    public Iterable<PDType> getAllTypes(){
        return pdTypeService.getAll();
    }
    @PostMapping("/editMany")
    public void edit(Iterable<PDType> types){
        for (PDType t:types){
            if(t.getId()==null){
                PDType p=new PDType();
                t.setId(p.getId());
            }
            pdTypeService.edit(t,t.getId());

        }

    }
}
