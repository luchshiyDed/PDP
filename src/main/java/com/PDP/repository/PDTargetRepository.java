package com.PDP.repository;

import com.PDP.model.PDTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PDTargetRepository extends BaseRepository<PDTarget,Long> {
    PDTarget findDistinctByName(String name);
}
