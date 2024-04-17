package com.PDP.service;

import com.PDP.model.AWP;
import com.PDP.model.ICOPD;
import com.PDP.repository.ICOPDRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class ICOPDService extends BaseService<ICOPD> {

    @Autowired
    private final AWPService awpService;
    @Autowired
    private final SubdivisionService subdivisionService;

    public ICOPDService(ICOPDRepository repository, AWPService awpService,SubdivisionService subdivisionService) {

        super(repository);
        this.subdivisionService=subdivisionService;
        this.awpService = awpService;
    }

    private void createMissingEntities(ICOPD icopd) {
        icopd.setSubdivision(subdivisionService.findByNameOrCreate(icopd.getSubdivision()));
        if (icopd.getAwps() != null) {
            ArrayList<AWP> newAwps = new ArrayList<>(icopd.getAwps().size());
            for (AWP awp : icopd.getAwps()) {
                newAwps.add(awpService.findByNameOrCreate(awp));
            }
            icopd.setAwps(newAwps);
        }
    }

    @Override
    public ICOPD findByNameOrCreate(ICOPD value) {
        if(value==null){
            return null;
        }
        createMissingEntities(value);
        return super.findByNameOrCreate(value);
    }

    @Override
    public HttpStatus createIfNotExists(ICOPD icopd) {
        if (icopd.getId() != null)
            if (repository.existsById(icopd.getId())) {
                return HttpStatus.OK;
            }
        createMissingEntities(icopd);
        repository.saveAndFlush(icopd);
        return HttpStatus.CREATED;
    }

    public HttpStatus activateById(Long id) {
        Optional<ICOPD> old = repository.findById(id);
        if (old.isPresent()) {
            old.get().setIsActive(Boolean.TRUE);
            old.get().setActivationDate(new Date());
            return HttpStatus.CREATED;
        }
        return HttpStatus.OK;
    }
    @Override
    public HttpStatus edit(ICOPD icopd, Long id) {

        Optional<ICOPD> old = repository.findById(id);
        createMissingEntities(icopd);
        if (old.isEmpty()) {
            repository.saveAndFlush(icopd);
            return HttpStatus.CREATED;
        }

        icopd.setId(old.get().getId());
        repository.saveAndFlush(icopd);
        return HttpStatus.OK;
    }

}
