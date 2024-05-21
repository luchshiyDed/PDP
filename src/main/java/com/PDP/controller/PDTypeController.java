package com.PDP.controller;

import com.PDP.service.PDTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/type")
public class PDTypeController {
    @Autowired
    private final PDTypeService service;
    @PostMapping
    public List<String> getAll(@RequestBody String prefix){
        return service.findByNamePrefix(prefix);
    }
}
