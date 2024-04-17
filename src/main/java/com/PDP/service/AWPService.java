package com.PDP.service;

import com.PDP.model.AWP;
import com.PDP.model.ISPDN;
import com.PDP.repository.AWPRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class AWPService extends BaseService<AWP>{
    public AWPService(AWPRepository repository, ISPDNService ispdnService) {
        super(repository);
        this.ispdnService = ispdnService;
    }
    @Autowired
    private final ISPDNService ispdnService;

    private void createMissingEntities(AWP awp) {
        if (awp.getIspdns() != null) {
            ArrayList<ISPDN> newISPDNS = new ArrayList<>(awp.getIspdns().size());
            for (ISPDN i : awp.getIspdns()) {
                newISPDNS.add(ispdnService.findByNameOrCreate(i));
            }
            awp.setIspdns(newISPDNS);
        }
    }
    @Override
    public HttpStatus createIfNotExists(AWP t) {
        if (t.getId() != null)
            if (repository.existsById(t.getId())) {
                return HttpStatus.OK;
            }
        createMissingEntities(t);
        repository.saveAndFlush(t);
        return HttpStatus.CREATED;
    }
    @Override
    public AWP findByNameOrCreate(AWP value){
        if(value==null){
            return null;
        }
        createMissingEntities(value);
        return super.findByNameOrCreate(value);
    }


    @Override
    public HttpStatus edit(AWP awp, Long id) {
        Optional<AWP> old = repository.findById(id);
        createMissingEntities(awp);
        if (old.isEmpty()) {
            repository.saveAndFlush(awp);
            return HttpStatus.CREATED;
        }

        awp.setId(old.get().getId());
        repository.saveAndFlush(awp);

        return HttpStatus.OK;
    }


}
