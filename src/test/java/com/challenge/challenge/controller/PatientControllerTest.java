package com.challenge.challenge.controller;

import com.challenge.challenge.model.Patient;
import com.challenge.challenge.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @BeforeEach
    public void setup() {
        reset(patientService);
    }

    @Test
    public void testGetAllPatients_NoFilters() throws Exception {
        List<Patient> patientsList = Arrays.asList(new Patient(1L, "Patient1", 30),
                new Patient(2L, "Patient2", 35));
        when(patientService.getAllPatients(isNull(), isNull(), any(Pageable.class))).thenReturn(new PageImpl<>(patientsList));

        mockMvc.perform(get("/api/patients/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patients.*", hasSize(2)))
                .andExpect(jsonPath("$.patients[0].name", is("Patient1")))
                .andExpect(jsonPath("$.patients[1].name", is("Patient2")));
    }

    @Test
    public void testGetAllPatients_WithNameFilter() throws Exception {
        List<Patient> filteredPatientsList = Arrays.asList(new Patient(1L, "Patient1", 30));
        when(patientService.getAllPatients(eq("Patient1"), isNull(), any(Pageable.class))).thenReturn(new PageImpl<>(filteredPatientsList));

        mockMvc.perform(get("/api/patients/all").param("name", "Patient1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patients.*", hasSize(1)))
                .andExpect(jsonPath("$.patients[0].name", is("Patient1")));
    }

    @Test
    public void testGetAllPatients_WithAgeFilter() throws Exception {
        List<Patient> filteredPatientsList = Arrays.asList(new Patient(1L, "Patient1", 30));
        when(patientService.getAllPatients(isNull(), eq(30), any(Pageable.class))).thenReturn(new PageImpl<>(filteredPatientsList));

        mockMvc.perform(get("/api/patients/all").param("age", "30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patients.*", hasSize(1)))
                .andExpect(jsonPath("$.patients[0].age", is(30)));
    }

    @Test
    public void testGetAllPatients_WithNameAndAgeFilter() throws Exception {
        List<Patient> filteredPatientsList = Arrays.asList(new Patient(1L, "Patient1", 30));
        when(patientService.getAllPatients(eq("Patient1"), eq(30), any(Pageable.class))).thenReturn(new PageImpl<>(filteredPatientsList));

        mockMvc.perform(get("/api/patients/all")
                        .param("name", "Patient1")
                        .param("age", "30"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patients.*", hasSize(1)))
                .andExpect(jsonPath("$.patients[0].name", is("Patient1")))
                .andExpect(jsonPath("$.patients[0].age", is(30)));
    }
}
