package com.PDP.repository;

import com.PDP.model.PDProcessType;
import com.PDP.model.PDType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PDProcessTypeRepository extends BaseRepository<PDProcessType,Long>{
    @Query(value = "select processType from PROCESSTYPE inner join PROCESS on process.PDProcessType=type.id where process.ispdn=?1",nativeQuery = true)
    List<PDProcessType> getPDProcessTypesByISPDNId(Long ispdnId);
}
