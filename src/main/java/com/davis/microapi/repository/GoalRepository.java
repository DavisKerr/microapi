package com.davis.microapi.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.davis.microapi.model.Goal;


public interface GoalRepository extends CrudRepository<Goal, Long> {
    
    Optional<Goal> findById(Long id);

    @Query(value = "SELECT * FROM goals g WHERE g.user_id = ?1", nativeQuery = true)
    List<Goal> findByUserId(Long userId);

    @Query(value = "SELECT * FROM goals g WHERE g.uuid = ?1 AND g.user_id = ?2", nativeQuery = true)
    Optional<Goal> findByUuidAndUserId(String uuid, Long userId);

    @Modifying
    @Transactional
    @Query("""
        update Goal u set 
        u.name = ?1,
        u.verb = ?2,
        u.measurement = ?3,
        u.quantity = ?4,
        u.period = ?5,
        u.startDate = ?6,
        u.endDate = ?7,
        u.completed = ?8,
        u.dateCreated = ?9,
        u.isTestData = ?10,
        u.deleted = ?11
        where u.uuid = ?12
            """)
    void setGoalByUuid(
        String name,
        String verb,
        String measurement,
        double quanitity,
        Integer period,
        String startDate,
        String endDate,
        boolean completed,
        String dateCreated,
        boolean isTestData,
        boolean deleted,
        String uuid
        );


}
