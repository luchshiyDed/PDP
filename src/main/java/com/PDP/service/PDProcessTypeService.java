package com.PDP.service;

import com.PDP.model.PDProcessType;
import com.PDP.repository.PDProcessTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDProcessTypeService extends BaseService<PDProcessType>{


    public PDProcessTypeService(PDProcessTypeRepository repository) {
        super(repository);
    }

}
