package com.client.client.controller;

import com.client.client.ClientApplication;
import com.client.client.entity.Gender;
import com.client.client.entity.Record;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = ClientApplication.class)
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private FileController fileController;

    private Record record;

    @Before
    public void init() throws ParseException {
        record = new Record();
        record.setId(1L);
        record.setStatus("late");
        record.setGender(Gender.FEMALE);
        record.setDateOfBirth(new SimpleDateFormat("dd/MM/yyyy").parse("22/04/1990"));
    }


    @Test
    public void findByGenderTest() throws Exception {

        when(fileController.findByGender(any(String.class))).thenReturn(Arrays.asList(record));

        this.mvc.perform(MockMvcRequestBuilders.get("/api/file").accept(MediaType.APPLICATION_JSON)
                .param("gender", "female"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gender", is(Gender.FEMALE.name())));
    }

    @Test
    public void findByLoanStateTest() throws Exception {

        when(fileController.findByLoanState("late")).thenReturn(Arrays.asList(record));

        this.mvc.perform(MockMvcRequestBuilders.get("/api/file").accept(MediaType.APPLICATION_JSON)
                .param("state", "late"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("late")));
    }

    @Test
    public void findByYearOfBirthGenderAndStateTest() throws Exception {

        when(fileController.findByYearOfBirthGenderAndState("female","late","1990")).thenReturn(Arrays.asList(record));

        this.mvc.perform(MockMvcRequestBuilders.get("/api/file").accept(MediaType.APPLICATION_JSON)
                .param("gender", "female")
                .param("state", "late")
                .param("year","1990"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].status", is("late")))
                .andExpect(jsonPath("$[0].gender", is(Gender.FEMALE.name())))
                .andExpect(jsonPath("$[0].dateOfBirth", is("22/04/1990")));
    }
}