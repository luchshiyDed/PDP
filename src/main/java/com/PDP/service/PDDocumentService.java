package com.PDP.service;

import com.PDP.model.PDDocument;
import com.PDP.repository.PDDocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service

public class PDDocumentService extends BaseService<PDDocument>{
    public PDDocumentService(PDDocumentRepository repository) {
        super(repository);
    }
}