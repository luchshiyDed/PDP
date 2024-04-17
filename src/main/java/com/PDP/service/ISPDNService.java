package com.PDP.service;

import com.PDP.model.ISPDN;
import com.PDP.model.Job;
import com.PDP.repository.ISPDNRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class ISPDNService extends BaseService<ISPDN>{
    public ISPDNService(ISPDNRepository repository) {
        super(repository);
    }

}
