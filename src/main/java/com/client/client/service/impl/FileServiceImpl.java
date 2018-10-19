package com.client.client.service.impl;

import com.client.client.entity.Record;
import com.client.client.service.FileService;
import com.client.client.service.ParsingService;
import com.client.client.service.TypeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileServiceImpl implements FileService {

    @Autowired
    private ParsingService parsingService;


    @Override
    public List<Record> findAllRecords() {
        return parsingService.getAllRecords();
    }

    @Override
    public List<Record> findByGender(String gender) {
        return parsingService.getAllRecords().stream().filter(record ->
                record.getGender() != null &&
                record.getGender().equals(TypeParser.parseInt(getGenderType(gender)))).collect(Collectors.toList());
    }

    @Override
    public List<Record> findByYear(String year) {
        return parsingService.getAllRecords().stream().filter(record -> {
            if(record.getDateOfBirth()==null)
                return false;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(record.getDateOfBirth());
            int recordYear = calendar.get(Calendar.YEAR);
            return recordYear == Integer.parseInt(year);
        }).collect(Collectors.toList());
    }

    @Override
    public List<Record> findByLoanState(String state) {
        return parsingService.getAllRecords().stream().filter(record -> record.getStatus().equalsIgnoreCase(state)).collect(Collectors.toList());
    }

    @Override
    public List<Record> findByYearOfBirthGenderAndState(String gender, String state, String year) {
        return parsingService.getAllRecords().stream().filter(record -> {
            if(record.getDateOfBirth()==null || record.getGender()==null || record.getStatus()==null)
                return false;
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(record.getDateOfBirth());
            int recordYear = calendar.get(Calendar.YEAR);
            return record.getGender().equals(TypeParser.parseInt(getGenderType(gender)))
                    && record.getStatus().equalsIgnoreCase(state)
                    && recordYear == Integer.parseInt(year);

        }).collect(Collectors.toList());
    }

    public String getGenderType(String gender){
        if(gender.equals("male"))
            return  "0";
        else if(gender.equals("female"))
            return  "1";
        else
            return "2";
    }

}
