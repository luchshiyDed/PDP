package com.PDP.service;

import com.PDP.model.PDDocument;
import com.PDP.model.PDProcessAction;
import com.PDP.repository.PDDocumentRepository;
import com.PDP.repository.PDProcessActionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PDProcessActionService extends BaseService<PDProcessAction> {

    public PDProcessActionService(PDProcessActionRepository repository) {
        super(repository);
    }
}
