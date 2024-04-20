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

    @PostMapping("/create")
    public void createAWP(Authentication auth,@RequestBody AWP awp) {
        awpService.createIfNotExists(awp);
    }

    @PostMapping("/edit/{id}")
    public void editAWP(Authentication auth,@PathVariable Long id, @RequestBody AWP awp) {

        awpService.edit(awp, id);

    }

    @DeleteMapping("/delete/{id}")
    public void deleteAWP(Authentication auth,@PathVariable Long id) {
        awpService.deleteById(id);
    }
    @PostMapping("/editMany")
    public void editEmployees(Authentication auth,@RequestBody List<AWP> awps){
        for(AWP awp1:awps){
            if(awp1.getId()==null){
                awpService.createIfNotExists(awp1);
                return;
            }

            awpService.edit(awp1, awp1.getId());
        }
    }
}
