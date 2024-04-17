package com.PDP.service;

import com.PDP.model.Employee;
import com.PDP.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class EmployeeService extends BaseService<Employee>{

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
        return super.findByNameOrCreate(value);
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


    @Override
    public HttpStatus edit(Employee employee, Long id) {
        Optional<Employee> old = repository.findById(id);
        createMissingEntities(employee);
        if (old.isEmpty()) {
            repository.saveAndFlush(employee);
            return HttpStatus.CREATED;
        }

        employee.setId(old.get().getId());
        repository.saveAndFlush(employee);
        return HttpStatus.OK;
    }
}
