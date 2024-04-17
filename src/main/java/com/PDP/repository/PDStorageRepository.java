package com.PDP.repository;

import com.PDP.model.PDStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDStorageRepository extends BaseRepository<PDStorage,Long> {
}
