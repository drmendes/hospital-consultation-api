package com.challenge.challenge.service;

import com.challenge.challenge.exceptions.DoctorNotFoundException;
import com.challenge.challenge.exceptions.PatientNotFoundException;
import com.challenge.challenge.model.*;
import com.challenge.challenge.model.dto.ConsultCreationDTO;
import com.challenge.challenge.model.dto.ConsultResponseDTO;
import com.challenge.challenge.repository.ConsultRepository;
import com.challenge.challenge.repository.DoctorRepository;
import com.challenge.challenge.repository.PatientRepository;
import com.challenge.challenge.repository.PathologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ConsultServiceTest {

    @InjectMocks
    private ConsultService consultService;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ConsultRepository consultRepository;

    @Mock
    private PathologyRepository pathologyRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateConsult_DoctorDoesNotExist() {
        when(doctorRepository.findByName("Maria")).thenReturn(Optional.empty());

        // Attempt to create a consult and expect an exception
        assertThrows(DoctorNotFoundException.class, () -> {
            consultService.createConsult(new ConsultCreationDTO("Maria", "Manuel", null));
        });
    }

    @Test
    void testCreateConsult_PatientDoesNotExist() {
        when(doctorRepository.findByName("Maria")).thenReturn(Optional.of(new Doctor(1L, "Maria", new Speciality())));

        when(patientRepository.findByName("Manuel")).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> {
            consultService.createConsult(new ConsultCreationDTO("Maria", "Manuel", null));
        });
    }


    @Test
    void testCreateConsult_NoPathologyPassed() {
        Doctor mockDoctor = new Doctor(1L, "Maria", new Speciality(1L, "Ophthalmology"));
        Patient mockPatient = new Patient(1L, "Manuel", 42);

        Consult mockConsult = new Consult(mockDoctor, mockDoctor.getSpeciality(), mockPatient, null);
        mockConsult.setId(1L); // Setting an ID for the mock consult

        when(doctorRepository.findByName("Maria")).thenReturn(Optional.of(mockDoctor));
        when(patientRepository.findByName("Manuel")).thenReturn(Optional.of(mockPatient));
        when(consultRepository.save(any(Consult.class))).thenReturn(mockConsult); // Mocking the save method

        ConsultResponseDTO consult = consultService.createConsult(new ConsultCreationDTO("Maria", "Manuel", null));

        assertNotNull(consult);
        assertNull(consult.getPathology());
    }

}
