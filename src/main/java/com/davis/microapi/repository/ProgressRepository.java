package com.davis.microapi.repository;

import com.davis.microapi.model.Progress;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ProgressRepository extends CrudRepository<Progress, Long> {
    
    Optional<Progress> findById(Long id);

    @Query(value = "SELECT * FROM progress p WHERE p.uuid = ?1 AND p.user_id = ?2", nativeQuery = true)
    Optional<Progress> findByUuidAndUserId(String uuid, Long userId);

    @Query(value = "SELECT * FROM progress p WHERE p.user_id = ?1", nativeQuery = true)
    List<Progress> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Progress p set 
        p.units = ?1,
        p.testData = ?2,
        p.deleted = ?3
        where p.uuid = ?4
            """)
    void setProgressByUuid(
        double units,
        Boolean testData,
        Boolean deleted,
        String uuid
        );
}
