package com.client.client.repository;

import com.client.client.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByGender(int gender);

    List<Record> findByStatus(String status);

    @Query(value = "SELECT r FROM Record r WHERE YEAR(r.dateOfBirth) = ?1 ")
    List<Record> findByDateOfBirth(int year);

    @Query(value = "SELECT r FROM Record r WHERE r.gender = ?1 and r.status = ?2 and YEAR(r.dateOfBirth) = ?3 ")
    List<Record> findByGenderAndStatusIgnoreCaseAndDateOfBirth(int gender, String state, int year);
}
