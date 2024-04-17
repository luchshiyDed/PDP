package com.PDP.service;


import com.PDP.model.Subdivision;
import com.PDP.repository.SubdivisionRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public class SubdivisionService extends BaseService<Subdivision>{

    public SubdivisionService(SubdivisionRepository repository) {
        super(repository);
    }
}
