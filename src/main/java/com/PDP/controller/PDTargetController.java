package com.PDP.controller;


import com.PDP.service.PDTargetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/pdtarget")
public class PDTargetController {
    @Autowired
    private final PDTargetService service;
    @PostMapping
    public List<String> getAll(@RequestBody String prefix){
        return service.findByNamePrefix(prefix);
    }
}
