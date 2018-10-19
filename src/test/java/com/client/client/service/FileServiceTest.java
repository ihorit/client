package com.client.client.service;

import com.client.client.entity.Gender;
import com.client.client.entity.Record;
import com.client.client.service.impl.FileServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FileServiceTest {

    @Mock
    private ParsingService parsingService;
    @InjectMocks
    private FileServiceImpl fileService;

    private List<Record> recordList;

    @Before
    public void init() throws ParseException {
        Record record = new Record();
        record.setId(1L);
        record.setStatus("late");
        record.setGender(Gender.FEMALE);
        record.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("22/04/1990"));

        recordList = Arrays.asList(record);

        when(parsingService.getAllRecords()).thenReturn(recordList);

    }

    @Test
    public void findByGenderTest(){
        List<Record> gender = fileService.findByGender("female");

        assertTrue(!gender.isEmpty());
        assertTrue(gender.get(0).getGender().equals(Gender.FEMALE));
    }

    @Test
    public void findByYearTest(){
        List<Record> year = fileService.findByYear("1990");

        assertTrue(!year.isEmpty());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(year.get(0).getDateOfBirth());
        int recordYear = calendar.get(Calendar.YEAR);
        assertTrue(recordYear == 1990);
    }

    @Test
    public void findByLoanStateTest(){
        List<Record> state = fileService.findByLoanState("late");

        assertTrue(!state.isEmpty());
        assertTrue(state.get(0).getStatus().equals("late"));

    }

    @Test
    public void findByYearOfBirthGenderAndStateTest(){
        List<Record> records = fileService.findByYearOfBirthGenderAndState("female","late","1990");

        assertTrue(!records.isEmpty());
        assertTrue(records.get(0).getGender().equals(Gender.FEMALE));
        assertTrue(records.get(0).getStatus().equals("late"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(records.get(0).getDateOfBirth());
        int recordYear = calendar.get(Calendar.YEAR);
        assertTrue(recordYear == 1990);
    }


}
