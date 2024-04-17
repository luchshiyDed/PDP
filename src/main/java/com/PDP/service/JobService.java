package com.PDP.service;

import com.PDP.model.Job;
import com.PDP.repository.JobRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobService extends BaseService<Job> {
    public JobService(JobRepository repository) {
        super(repository);
    }
}
