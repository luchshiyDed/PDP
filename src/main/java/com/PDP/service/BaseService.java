package com.PDP.service;


import com.PDP.model.Nameable;
import com.PDP.model.Subdivision;
import com.PDP.repository.BaseRepository;
import com.PDP.security.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@SuppressWarnings("unchecked")
public class BaseService<T extends Nameable> {
    protected final BaseRepository<T,Long> repository;
    public List<String> findByNamePrefix(String prefix){
        List<T> list=repository.findAll();
        prefix=prefix.replaceAll("\"","");
        prefix=prefix.toLowerCase();
        List<String> strings=new ArrayList<>();
        for (T t:list) {
            if(t.getName().toLowerCase().startsWith(prefix)) {
                strings.add(t.getName());
            }
        }
        return strings.stream().filter(s->s.equals("")).limit(5).toList();
    }
    private T createInstance(T inst){
        try {
            Constructor<T> t;
            t = (Constructor<T>) inst.getClass().getConstructor();
            return t.newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            System.err.println("Error creating new Entity");
            throw new RuntimeException(e);
        }
    }
    public BaseService(BaseRepository<T,Long> repository) {
        this.repository = repository;
    }
    public HttpStatus createIfNotExists(T t) {
        if (t.getId() != null)
            if (repository.existsById(t.getId())) {
                return HttpStatus.OK;
            }
        repository.saveAndFlush(t);
        return HttpStatus.CREATED;
    }
    public T getById(Long id){
        return repository.getReferenceById(id);
    }


    public T findByIdOrCreate(T value){
        if(value==null){
            return null;
        }
        if(value.getId()==null){
            T newValue=createInstance(value);
            value.setId(newValue.getId());
            repository.saveAndFlush(value);
            return value;
        }
        Optional<T> oldValue= repository.findById(value.getId());
        if(oldValue.isPresent()){
            return oldValue.get();
        }
        T newValue=createInstance(value);
        value.setId(newValue.getId());
        repository.saveAndFlush(value);
        return value;
    }
    public T findByNameOrCreate(T value){
        if(value==null){
            return null;
        }
        if (value.getName()==null){
            value.setName("");
        }
        value.setName(value.getName().replaceAll(",",""));
        Optional<T> oldValue= repository.findByName(value.getName());
        if(oldValue.isPresent()){
            return oldValue.get();
        }
        if(value.getId()==null){
            T newValue=createInstance(value);
            value.setId(value.getId());
            repository.saveAndFlush(value);
            return value;
        }
        return value;
    }
    public HttpStatus deleteById(Long id) {
        repository.deleteById(id);
        return HttpStatus.OK;
    }
    public HttpStatus edit(T t, Long id){
        Optional<T> old = repository.findById(id);
        if (old.isEmpty()) {
            repository.saveAndFlush(t);
            return HttpStatus.CREATED;
        }
        t.setId(old.get().getId());
        repository.saveAndFlush(t);
        return HttpStatus.OK;
    }
    public List<T> getAll() {
        return repository.findAll().stream().filter(e->!e.getName().equals("")).toList();
    }
}
