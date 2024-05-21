package com.PDP.controller;

import com.PDP.service.DeleteConditionService;
import com.PDP.service.PDProcessActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/deletecondition")
public class DeleteConditionController {
    @Autowired
    private final DeleteConditionService service;
    @PostMapping
    public List<String> getAll(@RequestBody String prefix){
        return service.findByNamePrefix(prefix);
    }
}
