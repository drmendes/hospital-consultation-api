package com.challenge.challenge.controller;

import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import com.challenge.challenge.service.SpecialityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SpecialityControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecialityService specialityService;

    private List<SpecialityPatientCountDTO> mockSpecialities;

    @BeforeEach
    public void setUp() {
        mockSpecialities = Arrays.asList(
                new SpecialityPatientCountDTO("Cardiology", 5),
                new SpecialityPatientCountDTO("Neurology", 3)
        );
    }

    @Test
    public void testGetTopSpecialitiesWithoutThreshold() throws Exception {
        when(specialityService.getSpecialitiesWithPatientCountAboveThreshold(any())).thenReturn(mockSpecialities);

        mockMvc.perform(get("/api/specialities/top")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetTopSpecialitiesWithThreshold() throws Exception {
        when(specialityService.getSpecialitiesWithPatientCountAboveThreshold(any())).thenReturn(mockSpecialities);

        mockMvc.perform(get("/api/specialities/top")
                        .param("threshold", "4")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
