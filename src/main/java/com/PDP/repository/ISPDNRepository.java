package com.PDP.repository;

import com.PDP.model.ISPDN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISPDNRepository extends BaseRepository<ISPDN,Long>{
    Optional<ISPDN> findByName(String name);
}
