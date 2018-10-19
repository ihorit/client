package com.client.client.service;

import com.client.client.entity.Record;

import java.util.List;

public interface FileService {

    List<Record> findAllRecords();

    List<Record> findByGender(String gender);

    List<Record> findByYear(String year);

    List<Record> findByLoanState(String state);

    List<Record> findByYearOfBirthGenderAndState(String gender, String state, String year);
}
