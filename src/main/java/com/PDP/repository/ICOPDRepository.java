package com.PDP.repository;

import com.PDP.model.ICOPD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICOPDRepository extends BaseRepository<ICOPD,Long> {

}
