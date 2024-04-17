package com.PDP.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@NoRepositoryBean
public interface BaseRepository<T,Long> extends JpaRepository<T,Long> {
    Optional<T> findByName(String name);
}
