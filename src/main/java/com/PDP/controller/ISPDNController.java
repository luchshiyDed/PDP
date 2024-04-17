package com.PDP.controller;

import com.PDP.model.ISPDN;
import com.PDP.service.ISPDNService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
