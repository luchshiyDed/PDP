package com.PDP.repository;

import com.PDP.model.AWP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AWPRepository extends BaseRepository<AWP,Long> {
}
