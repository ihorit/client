package com.client.client.controller;

import com.client.client.service.ParsingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    public static final String address = "https://s3-eu-west-1.amazonaws.com/b2bmobiledata.fidomockapp/dev/loans_data.csv";

    @Autowired
    private ParsingService parsingService;

    Logger LOGGER = LoggerFactory.getLogger(ApiController.class);

    @RequestMapping(value = "/api/data/sync", method = RequestMethod.GET)
    @ResponseBody
    public void downloadAndSaveTestFile(){
        parsingService.downloadAndSaveTestFile(address, "loan_data.csv");
    }

    @RequestMapping(value = "/api/data/test",params = "name", method = RequestMethod.GET)
    public void testLogger(@RequestParam("name") String name){
        LOGGER.debug("debugging...");
    }
}
