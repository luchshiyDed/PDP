package com.PDP.repository;

import com.PDP.model.PDProcess;
import com.PDP.util.reports.ISPDNReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PDProcessRepository extends BaseRepository<PDProcess,Long> {

}
