package com.PDP.repository;

import com.PDP.model.PDProcessAction;
import com.PDP.model.PDType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PDProcessActionRepository extends BaseRepository<PDProcessAction,Long>{
    @Query(value = "select processAction from PROCESSACTION inner join PROCESS on process.PDProcessAction=processAction.id where process.ispdn=?1",nativeQuery = true)
    List<PDProcessAction> getPDProcessActionByISPDNId(Long ispdnId);
}
