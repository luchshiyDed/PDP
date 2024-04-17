package com.PDP.service;

import com.PDP.model.PDRegDoc;
import com.PDP.repository.PDRegDocRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class PDRegDocService extends BaseService<PDRegDoc> {

    public PDRegDocService(PDRegDocRepository repository) {
        super(repository);
    }
}
