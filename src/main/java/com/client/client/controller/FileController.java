package com.client.client.controller;

import com.client.client.entity.Record;
import com.client.client.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;


    @ResponseBody
    @RequestMapping(value = "/api/file", method = RequestMethod.GET)
    public List<Record> readAll() {
        return fileService.findAllRecords();
    }

    @ResponseBody
    @RequestMapping(value = "/api/file", params = "gender", method = RequestMethod.GET)
    public List<Record> findByGender(@RequestParam("gender") String gender) {
        return fileService.findByGender(gender);
    }

    @ResponseBody
    @RequestMapping(value = "/api/file", params = "state", method = RequestMethod.GET)
    public List<Record> findByLoanState(@RequestParam("state") String state) {
        return fileService.findByLoanState(state);
    }

    @ResponseBody
    @RequestMapping(value = "/api/file", params = "year", method = RequestMethod.GET)
    public List<Record> findByYear(@RequestParam("year") String year) {
        return fileService.findByYear(year);
    }

    @ResponseBody
    @RequestMapping(value = "/api/file", params = {"gender", "state", "year"}, method = RequestMethod.GET)
    public List<Record> findByYearOfBirthGenderAndState(@RequestParam("gender") String gender,
                                                        @RequestParam("state") String state, @RequestParam("year") String year){
        return fileService.findByYearOfBirthGenderAndState(gender, state, year);
    }


}
