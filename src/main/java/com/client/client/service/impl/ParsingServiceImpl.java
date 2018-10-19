package com.client.client.service.impl;

import com.client.client.entity.Record;
import com.client.client.repository.RecordRepository;
import com.client.client.service.ParsingService;
import com.client.client.service.TypeParser;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class ParsingServiceImpl implements ParsingService {
    @Autowired
    private RecordRepository recordRepository;
    private List<Record> allRecords;


    @Override
    public List<Record> getAllRecords() {
        if (allRecords == null)
            return new ArrayList<>();
        return allRecords;
    }

    @Override
    public boolean downloadAndSaveTestFile(String address, String fileName) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(address).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("src/main/resources/loan_data.csv")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;

            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            allRecords = recordRepository.save(parseCVSFile());

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Record> parseCVSFile(){
        List<Record> records = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/main/resources/loan_data.csv"));
            CSVReader csvReader = new CSVReader(reader);
            List<String[]> strings = csvReader.readAll();
            strings.remove(0);

            for (String[] string : strings){
                Record record = new Record();
                record.setId(TypeParser.parseLong(string[0]));
                record.setStatus(string[1]);
                record.setAmount(Double.parseDouble(string[2]));
                record.setApplicationSignedHour(TypeParser.parseInt(string[3]));
                record.setApplicationSignedWeekday(TypeParser.parseInt(string[4]));

                record.setCity(string[5]);
                record.setCountry(string[6]);
                record.setCreditScoreEsEquifaxRisk(string[7]);
                record.setDateOfBirth(TypeParser.parseDate(string[8]));
                record.setDebtToIncome(TypeParser.parseDouble(string[9]));
                record.setEducation(TypeParser.parseInt(string[10]));
                record.setEmploymentDurationCurrentEmployer(string[11]);
                record.setEmploymentPosition(string[12]);
                record.setEmploymentStatus(TypeParser.parseInt(string[13]));

                record.setExistingLiabilities(TypeParser.parseInt(string[14]));
                record.setGender(TypeParser.parseGender(string[15]));
                record.setHomeOwnershipType(TypeParser.parseInt(string[16]));

                record.setIncomeFromPrincipalEmployer(TypeParser.parseInt(string[17]));
                record.setIncomeTotal(TypeParser.parseInt(string[18]));
                record.setInterestRateAPR(TypeParser.parseDouble(string[19]));

                record.setLoanDate(TypeParser.parseDate(string[20]));
                record.setLoanDuration(TypeParser.parseInt(string[21]));
                record.setMaritalStatus(TypeParser.parseInt(string[22]));

                record.setNewCreditCustomer(TypeParser.parseBoolean(string[23]));
                record.setNoOfPreviousLoansBeforeLoan(TypeParser.parseInt(string[24]));
                record.setOccupationArea(TypeParser.parseInt(string[25]));

                record.setUseOfLoan(TypeParser.parseInt(string[26]));
                record.setVerificationType(TypeParser.parseInt(string[27]));
                record.setWorkExperience(string[28]);

                record.setPreviousScore(TypeParser.parseDouble(string[29]));
                record.setDefaulted(TypeParser.parseBoolean(string[30]));
                record.setDefaultDate(TypeParser.parseDate(string[31]));

                records.add(record);
            }
            return records;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
