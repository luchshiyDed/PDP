package com.PDP.service;

import com.PDP.model.PDType;
import com.PDP.repository.PDTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDTypeService extends BaseService<PDType>{

    public PDTypeService(PDTypeRepository repository) {
        super(repository);
    }
}
