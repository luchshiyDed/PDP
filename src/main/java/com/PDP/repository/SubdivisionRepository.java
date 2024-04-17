package com.PDP.repository;

import com.PDP.model.Subdivision;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubdivisionRepository extends BaseRepository<Subdivision,Long> {
}
