package com.PDP.service;

import com.PDP.model.PDStorage;
import com.PDP.repository.PDStorageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class PDStorageService extends BaseService<PDStorage> {

    public PDStorageService(PDStorageRepository repository) {
        super(repository);
    }
}
