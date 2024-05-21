package com.PDP.controller;

import com.PDP.model.ICOPD;
import com.PDP.model.ISPDN;
import com.PDP.service.ISPDNService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ispdn")
public class ISPDNController {
    @Autowired
    private final ISPDNService ispdnService;
    @GetMapping
    public Iterable<ISPDN> getAllISPDNs() {
        return ispdnService.getAll();
    }
    @PostMapping("/edit/{id}")
    public void editISPDN(@PathVariable Long id, @RequestBody ISPDN ispdn){
         ispdnService.edit(ispdn,id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteISPDN(@PathVariable Long id){
         ispdnService.deleteById(id);
    }
    @PostMapping("/editMany")
    public void editICOPDs(Authentication auth, @RequestBody List<ISPDN> ispdns){
        for(ISPDN ispdn1:ispdns){
            if(ispdn1.getId()==null){
                ispdnService.findByNameOrCreate(ispdn1);
            }
            else
                ispdnService.edit(ispdn1, ispdn1.getId());
        }
    }
}

