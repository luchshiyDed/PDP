package com.PDP.controller;

import com.PDP.model.ICOPD;
import com.PDP.model.PDProcess;
import com.PDP.service.ICOPDService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ICOPD")
public class ICOPDController {
    @Autowired
    private final ICOPDService icopdService;
    @GetMapping
    public Iterable<ICOPD> getAllICOPDs(Authentication auth) {
        return icopdService.getAll();
    }
    @PostMapping("/create")
    public void createICOPD(Authentication auth,@RequestBody ICOPD icopd){
        icopdService.createIfNotExists(icopd);
    }
    @PostMapping("/edit/{id}")
    public void editICOPD(Authentication auth,@PathVariable Long id, @RequestBody ICOPD icopd){
        icopdService.edit(icopd,id);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteICOPD(Authentication auth,@PathVariable Long id){
        icopdService.deleteById(id);
    }
    @PostMapping("/editMany")
    public void editICOPDs(Authentication auth,@RequestBody List<ICOPD> icopds){
        for(ICOPD icopd1:icopds){
            if(icopd1.getId()==null){
                icopdService.createIfNotExists(icopd1);
                return;
            }

            icopdService.edit(icopd1, icopd1.getId());
        }
    }
}
