package com.PDP.repository;

import com.PDP.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends BaseRepository<Job,Long> {
}
