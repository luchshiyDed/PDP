package com.PDP.service;

import com.PDP.model.SubdivisionData;
import com.PDP.repository.BaseRepository;
import com.PDP.security.user.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public class AuthCheckingService<T extends SubdivisionData> extends BaseService<T> {

    public void delete(Authentication authentication,Long id){
        Optional<T> t=repository.findById(id);
        if(t.isPresent())
            if(checkAuth((UserEntity)authentication.getPrincipal(),t.get())){
                repository.deleteById(id);
            }
    }
    public boolean checkAuth(UserEntity user,T t){
        return user.getAllSubdivisions() || t.getSubdivisionName().equals(user.getSubdivision());
    }
    public AuthCheckingService(BaseRepository<T, Long> repository) {
        super(repository);
    }
}
