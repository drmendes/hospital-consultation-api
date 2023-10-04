package com.challenge.challenge.controller;

import com.challenge.challenge.controller.ConsultController;
import com.challenge.challenge.service.ConsultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConsultControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ConsultService consultService;

    @Autowired
    private ConsultController consultController;

    @BeforeEach
    public void setUp() {
        // Initialization if needed
    }

    @Test
    void testGetAllConsults() throws Exception {
        // Performing the test request and asserting results
        mockMvc.perform(get("/api/consults"))
                .andExpect(status().isOk());
    }


}
