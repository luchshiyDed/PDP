package com.PDP.controller;

import com.PDP.service.PDProcessActionService;
import com.PDP.service.PDSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/pdprocessaction")
public class PDProcessActionController {
    @Autowired
    private final PDProcessActionService service;
    @PostMapping
    public List<String> getAll(@RequestBody String prefix){
        return service.findByNamePrefix(prefix);
    }
}
