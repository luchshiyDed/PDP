package com.PDP.service;

import com.PDP.model.PDTarget;
import com.PDP.repository.PDTargetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class PDTargetService extends BaseService<PDTarget>{

    public PDTargetService(PDTargetRepository repository) {
        super(repository);
    }
}
