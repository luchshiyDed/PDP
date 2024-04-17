package com.PDP.controller;
import com.PDP.model.Employee;
import com.PDP.security.user.UserEntity;
import com.PDP.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private final EmployeeService employeeService;
    @GetMapping
    public Iterable<Employee> getAllEmployees(Authentication auth) {
        UserEntity user= (UserEntity) auth.getPrincipal();
            if(user.getAllSubdivisions())
                return employeeService.getAll();
            return employeeService.getAll().stream().filter(employee -> {
             return employee.getSubdivision().getName().equals(user.getSubdivision());
        }).toList();
    }
    @PostMapping("/create")
    public void createEmployee(Authentication auth,@RequestBody Employee employee){
        employeeService.createIfNotExists(employee);
    }
    @PostMapping("/edit/{id}")
    public void editEmployee(Authentication auth,@PathVariable Long id, @RequestBody Employee employee){

        employeeService.edit(employee,id);

    }
    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(Authentication auth,@PathVariable Long id){
        employeeService.deleteById(id);
    }
}
