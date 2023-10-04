package com.challenge.challenge.service;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Speciality;
import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import com.challenge.challenge.repository.ConsultRepository;
import com.challenge.challenge.repository.SpecialityRepository;
import com.challenge.challenge.service.SpecialityService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;

import java.util.Collections;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SpecialityServiceTest {

    @InjectMocks
    private SpecialityService specialityService;

    @Mock
    private ConsultRepository consultRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTopSpecialities_NormalScenario() {
        List<SpecialityPatientCountDTO> mockList = Arrays.asList(
                new SpecialityPatientCountDTO("Speciality1", 5),
                new SpecialityPatientCountDTO("Speciality2", 4),
                new SpecialityPatientCountDTO("Speciality3", 3)
        );


        when(consultRepository.findSpecialitiesWithPatientCountAboveThreshold(2)).thenReturn(mockList);

        List<SpecialityPatientCountDTO> result = specialityService.getSpecialitiesWithPatientCountAboveThreshold(Optional.of(2));

        assertEquals(3, result.size());
        assertEquals("Speciality1", result.get(0).getSpecialityName());
        assertEquals(5L, result.get(0).getPatientCount());
        assertEquals("Speciality2", result.get(1).getSpecialityName());
        assertEquals(4L, result.get(1).getPatientCount());
        assertEquals("Speciality3", result.get(2).getSpecialityName());
        assertEquals(3L, result.get(2).getPatientCount());
    }


    @Test
    void testGetTopSpecialities_NoThresholdProvided() {
        List<SpecialityPatientCountDTO> mockList = Arrays.asList(
                new SpecialityPatientCountDTO("Speciality1", 5),
                new SpecialityPatientCountDTO("Speciality2", 4)
        );

        when(consultRepository.findSpecialitiesWithPatientCountAboveThreshold(2)).thenReturn(mockList);

        List<SpecialityPatientCountDTO> result = specialityService.getSpecialitiesWithPatientCountAboveThreshold(Optional.empty());

        assertEquals(2, result.size());
        assertEquals("Speciality1", result.get(0).getSpecialityName());
        assertEquals("Speciality2", result.get(1).getSpecialityName());
    }


    @Test
    void testGetTopSpecialities_LessThanLimit() {
        List<SpecialityPatientCountDTO> mockList = Arrays.asList(
                new SpecialityPatientCountDTO("Speciality1", 1)
        );

        when(consultRepository.findSpecialitiesWithPatientCountAboveThreshold(2)).thenReturn(mockList);

        List<SpecialityPatientCountDTO> result = specialityService.getSpecialitiesWithPatientCountAboveThreshold(Optional.of(2));

        assertEquals(1, result.size());
        assertEquals("Speciality1", result.get(0).getSpecialityName());
    }

    @Test
    void testGetTopSpecialities_Empty() {
        when(consultRepository.findSpecialitiesWithPatientCountAboveThreshold(2)).thenReturn(Collections.emptyList());

        List<SpecialityPatientCountDTO> result = specialityService.getSpecialitiesWithPatientCountAboveThreshold(Optional.of(2));

        assertEquals(0, result.size());
    }

}
