package com.client.client.controller;

import com.client.client.entity.Record;
import com.client.client.repository.RecordRepository;
import com.client.client.service.TypeParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DatabaseController {

    @Autowired
    private RecordRepository recordRepository;

    @ResponseBody
    @RequestMapping(value = "/api/db", method = RequestMethod.GET)
    public List<Record> readAll() {
        return recordRepository.findAll();
    }

    @ResponseBody
    @RequestMapping(value = "/api/db", params = "gender", method = RequestMethod.GET)
    public List<Record> findByGender(@RequestParam("gender") String gender) {
        return recordRepository.findByGender(TypeParser.parseGender(gender));
    }

    @ResponseBody
    @RequestMapping(value = "/api/db", params = "status", method = RequestMethod.GET)
    public List<Record> findByLoanState(@RequestParam("status") String status) {
        return recordRepository.findByStatus(status);
    }

    @ResponseBody
    @RequestMapping(value = "/api/db", params = "year", method = RequestMethod.GET)
    public List<Record> findByYearOfBirth(@RequestParam("year") String year) {
        return recordRepository.findByDateOfBirth(TypeParser.parseInt(year));
    }

    @ResponseBody
    @RequestMapping(value = "/api/db", params = {"gender", "state", "year"}, method = RequestMethod.GET)
    public List<Record> findByYearOfBirthGenderAndState(@RequestParam("gender") String gender,
                                                         @RequestParam("state") String state, @RequestParam("year") String year) {
        return recordRepository.findByGenderAndStatusIgnoreCaseAndDateOfBirth(TypeParser.parseGender(gender), state, TypeParser.parseInt(year));
    }
}
