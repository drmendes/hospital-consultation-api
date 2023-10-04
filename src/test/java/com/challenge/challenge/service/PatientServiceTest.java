package com.challenge.challenge.service;

import com.challenge.challenge.exceptions.PatientNotFoundException;
import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Doctor;
import com.challenge.challenge.model.Patient;
import com.challenge.challenge.model.Pathology;
import com.challenge.challenge.model.Speciality;
import com.challenge.challenge.repository.ConsultRepository;
import com.challenge.challenge.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@MockitoSettings(strictness = Strictness.LENIENT)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ConsultRepository consultRepository;

    @InjectMocks
    private PatientService patientService;

    @BeforeEach
    public void setUp() {
        // Common setup can go here if necessary
    }

    @Test
    void testGetConsultsForPatient_ValidId() {
        // Mocking data
        Doctor doctor1 = new Doctor(1L, "Doctor1", new Speciality(1L, "Speciality1"));
        Doctor doctor2 = new Doctor(2L, "Doctor2", new Speciality(2L, "Speciality2"));

        Pathology pathology1 = new Pathology(1L, "Pathology1");
        Pathology pathology2 = new Pathology(2L, "Pathology2");

        Patient mockPatient = new Patient(1L, "Patient1", 30);
        Consult consult1 = new Consult(doctor1, doctor1.getSpeciality(), mockPatient, pathology1);
        Consult consult2 = new Consult(doctor2, doctor2.getSpeciality(), mockPatient, pathology2);

        List<Consult> consults = Arrays.asList(consult1, consult2);
        when(patientRepository.existsById(1L)).thenReturn(true);
        when(patientRepository.findById(1L)).thenReturn(Optional.of(mockPatient));

        when(consultRepository.findByPatientId(mockPatient.getId())).thenReturn(consults);

        List<Consult> result = patientService.getConsultsForPatient(1L);

        assertEquals(2, result.size());
        assertEquals("Doctor1", result.get(0).getDoctor().getName());
        assertEquals("Doctor2", result.get(1).getDoctor().getName());
    }

    @Test
    void testGetConsultsForPatient_InvalidId() {
        when(patientRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> {
            patientService.getConsultsForPatient(99L);
        });
    }
}
