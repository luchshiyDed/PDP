package com.PDP.service;

import com.PDP.model.Employee;
import com.PDP.repository.EmployeeRepository;
import com.PDP.security.user.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService extends AuthCheckingService<Employee>{

    @Autowired
    private final JobService jobService;
    @Autowired
    private final AWPService awpService;
    @Autowired
    private final SubdivisionService subdivisionService;

    public EmployeeService(EmployeeRepository repository, JobService jobService, AWPService awpService, SubdivisionService subdivisionService) {
        super(repository);
        this.jobService = jobService;
        this.awpService = awpService;
        this.subdivisionService = subdivisionService;
    }

    @Override
    public Employee findByNameOrCreate(Employee value) {
        if(value==null){
            return null;
        }

        createMissingEntities(value);
        if (value.getEmail()==null){
            value.setEmail("");
        }
        Optional<Employee> oldValue= ((EmployeeRepository)repository).findByEmail(value.getEmail());
        if(oldValue.isPresent()){
            System.out.println(oldValue.get());
            return oldValue.get();
        }
        if(value.getId()==null){
            Employee newValue=new Employee();
            value.setId(value.getId());
            repository.saveAndFlush(value);
            return value;
        }
        System.out.println(1);
        return value;
    }

    private void createMissingEntities(Employee employee) {
        employee.setJob(jobService.findByNameOrCreate(employee.getJob()));
        employee.setAwp(awpService.findByNameOrCreate(employee.getAwp()));
        employee.setSubdivision(subdivisionService.findByNameOrCreate(employee.getSubdivision()));
    }

    @Override
    public HttpStatus createIfNotExists(Employee employee) {
        if (employee.getId() != null)
            if (repository.existsById(employee.getId()))
                return HttpStatus.OK;
        createMissingEntities(employee);
        repository.saveAndFlush(employee);
        return HttpStatus.CREATED;
    }



    public HttpStatus edit(Authentication authentication,Employee employee, Long id) {
        UserEntity user= (UserEntity) authentication.getPrincipal();
        Optional<Employee> old = repository.findById(id);
        createMissingEntities(employee);
        if (old.isEmpty()) {
            if(checkAuth(user,employee)){
                repository.saveAndFlush(employee);
                return HttpStatus.CREATED;
            }
            return HttpStatus.FORBIDDEN;

        }
        System.out.println(employee);
        employee.setId(old.get().getId());
        System.out.println(employee);
        if(checkAuth(user,old.get())){
            repository.saveAndFlush(employee);
            return HttpStatus.OK;
        }
        return HttpStatus.FORBIDDEN;

    }
    @Override
    public List<Employee> getAll() {
        return repository.findAll().stream().filter(e->!e.getEmail().equals("")).toList();
    }
}
