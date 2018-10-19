package com.client.client.repository;

import com.client.client.entity.Record;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecordRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RecordRepository recordRepository;


    @Test
    public void findByGenderTest(){

        Record record = new Record();
        record.setId(1L);
        record.setGender(1);

        entityManager.persist(record);
        entityManager.flush();

        List<Record> male = recordRepository.findByGender(1);

        assertTrue(!male.isEmpty());
        assertTrue(male.get(0).getGender() == 1);

    }

    @Test
    public void findByLoanStateTest(){

        Record record = new Record();
        record.setId(1L);
        record.setStatus("late");

        entityManager.persist(record);
        entityManager.flush();

        List<Record> state = recordRepository.findByStatus("late");

        assertTrue(!state.isEmpty());
        assertTrue(state.get(0).getStatus().equals("late"));

        List<Record> states = recordRepository.findByStatus("asd");
        assertTrue(states.isEmpty());

    }

    @Test
    public void findByYearOfBirthTest() throws ParseException {
        Record record = new Record();
        record.setId(1L);
        record.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("22/04/1990"));

        entityManager.persist(record);
        entityManager.flush();

        List<Record> year = recordRepository.findByDateOfBirth(1990);

        assertTrue(!year.isEmpty());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(year.get(0).getDateOfBirth());
        int recordYear = calendar.get(Calendar.YEAR);

        assertTrue(recordYear == 1990);

        List<Record> years = recordRepository.findByStatus("1790");
        assertTrue(years.isEmpty());

    }

    @Test
    public void findByYearOfBirthGenderAndStateTest() throws ParseException {
        Record record = new Record();
        record.setId(1L);
        record.setStatus("late");
        record.setGender(1);
        record.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("22/04/1990"));

        entityManager.persist(record);
        entityManager.flush();

        List<Record> records = recordRepository.findByGenderAndStatusIgnoreCaseAndDateOfBirth(1,"late",1990);

        assertTrue(!records.isEmpty());
        assertTrue(records.get(0).getStatus().equals("late"));
        assertTrue(records.get(0).getGender() == 1);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(records.get(0).getDateOfBirth());
        int recordYear = calendar.get(Calendar.YEAR);
        assertTrue(recordYear == 1990);
    }
}
