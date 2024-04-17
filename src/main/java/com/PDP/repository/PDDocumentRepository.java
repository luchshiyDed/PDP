package com.PDP.repository;

import com.PDP.model.PDDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDDocumentRepository extends BaseRepository<PDDocument,Long>{
}
