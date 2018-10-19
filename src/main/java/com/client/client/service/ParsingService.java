package com.client.client.service;

import com.client.client.entity.Record;

import java.util.List;

public interface ParsingService {

    List<Record> getAllRecords();

    boolean downloadAndSaveTestFile(String address, String fileName);
}
