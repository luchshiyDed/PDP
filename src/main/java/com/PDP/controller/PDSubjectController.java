package com.PDP.controller;

import com.PDP.model.PDSubject;
import com.PDP.service.PDSubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/subject")
public class PDSubjectController {
    @Autowired
    private final PDSubjectService service;
    @PostMapping
    public List<String> getAll(@RequestBody String prefix){
        return service.findByNamePrefix(prefix);
    }

}
