package com.PDP.repository;

import com.PDP.model.PDSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PDSubjectRepository extends BaseRepository<PDSubject,Long>{
    @Query(value = "select distinct(subject) from process inner join subject where process.icopd=?1 and subject.id=process.subject",nativeQuery = true)
    List<PDSubject> getPDSubjectsByICOPD(Long icopdID);
}
