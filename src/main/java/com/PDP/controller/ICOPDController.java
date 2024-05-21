package com.PDP.controller;

import com.PDP.model.ICOPD;
import com.PDP.security.user.UserEntity;
import com.PDP.service.ICOPDService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/icopd")
public class ICOPDController {
    @Autowired
    private final ICOPDService icopdService;
    @GetMapping
    public Iterable<ICOPD> getAllICOPDs(Authentication auth) {
        UserEntity user = (UserEntity) auth.getPrincipal();
        if (user.getAllSubdivisions())
            return icopdService.getAll();
        return icopdService.getAll().stream().filter(icopd -> {
            return icopd.getSubdivisionName().equals(user.getSubdivision());
        }).toList();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteICOPD(Authentication auth,@PathVariable Long id){
        icopdService.delete(auth,id);
    }
    @PostMapping("/editMany")
    public void editICOPDs(Authentication auth,@RequestBody List<ICOPD> icopds){
        for(ICOPD icopd1:icopds){
            if(icopd1.getId()==null){
                icopdService.findByNameOrCreate(icopd1);
            }
            else
                icopdService.edit(auth,icopd1, icopd1.getId());
        }
    }
}
