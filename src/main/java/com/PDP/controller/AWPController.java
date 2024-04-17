package com.PDP.controller;

import com.PDP.model.AWP;
import com.PDP.service.AWPService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

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
}
