package com.challenge.challenge.service;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Speciality;
import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import com.challenge.challenge.repository.ConsultRepository;
import com.challenge.challenge.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private ConsultRepository consultRepository;

    public List<SpecialityPatientCountDTO> getSpecialitiesWithPatientCountAboveThreshold(Optional<Integer> threshold) {
        return consultRepository.findSpecialitiesWithPatientCountAboveThreshold(threshold.orElse(2));
    }
}
