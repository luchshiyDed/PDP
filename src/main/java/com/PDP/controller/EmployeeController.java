package com.PDP.controller;

import com.PDP.model.Employee;
import com.PDP.security.user.UserEntity;
import com.PDP.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;

    @GetMapping
    public Iterable<Employee> getAllEmployees(Authentication auth) {
        UserEntity user = (UserEntity) auth.getPrincipal();
        if (user.getAllSubdivisions())
            return employeeService.getAll();
        return employeeService.getAll().stream().filter(employee -> {
            return employee.getSubdivisionName().equals(user.getSubdivision());
        }).toList();
    }
    @PostMapping("/employee")
    public List<String> getAll(@RequestBody String prefix){
        return employeeService.findByNamePrefix(prefix);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(Authentication auth, @PathVariable Long id) {
        employeeService.delete(auth,id);
    }

    @PostMapping("/editMany")
    public void editEmployees(Authentication auth, @RequestBody List<Employee> employees) {
        for (Employee employee1 : employees) {
            if (employee1.getId() == null) {
                employeeService.findByNameOrCreate(employee1);

            } else
                employeeService.edit(auth,employee1, employee1.getId());
        }
    }
}
