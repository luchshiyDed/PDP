package com.PDP.repository;

import com.PDP.model.PDRegDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDRegDocRepository extends BaseRepository<PDRegDoc,Long> {
}
