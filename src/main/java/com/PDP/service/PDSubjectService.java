package com.PDP.service;

import com.PDP.model.PDSubject;
import com.PDP.repository.PDSubjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDSubjectService extends BaseService<PDSubject> {
    public PDSubjectService(PDSubjectRepository repository) {
        super(repository);
    }
}
