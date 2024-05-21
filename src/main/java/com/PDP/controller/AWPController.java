package com.PDP.controller;

import com.PDP.model.AWP;
import com.PDP.model.ICOPD;
import com.PDP.service.AWPService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/awp")
public class AWPController {
    @Autowired
    private final AWPService awpService;

    @GetMapping
    public Iterable<AWP> getAllAWPs(Authentication auth) {
        return awpService.getAll();
    }

    @PostMapping("/awp")
    public List<String> getAll(@RequestBody String prefix){
        return awpService.findByNamePrefix(prefix);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteAWP(Authentication auth,@PathVariable Long id) {
        awpService.delete(auth,id);
    }
    @PostMapping("/editMany")
    public void editEmployees(Authentication auth,@RequestBody List<AWP> awps){
        for(AWP awp1:awps){
            if(awp1.getId()==null){
                awpService.findByNameOrCreate(awp1);
                return;
            }

            awpService.edit(awp1, awp1.getId());
        }
    }
}
