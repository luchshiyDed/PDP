package com.PDP.repository;

import com.PDP.model.PDType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PDTypeRepository extends BaseRepository<PDType,Long> {
    @Query(value = "select TYPE from TYPE inner join PROCESS on PROCESS.TYPE_ID=TYPE.id where process.ispdn_id=?1",nativeQuery = true)
    List<PDType> getPDTypesByISPDNId(Long ispdnId);
}
