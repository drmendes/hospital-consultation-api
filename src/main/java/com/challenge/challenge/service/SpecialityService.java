package com.challenge.challenge.service;

import com.challenge.challenge.model.dto.SpecialityPatientCountDTO;
import com.challenge.challenge.repository.ConsultRepository;
import com.challenge.challenge.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    @Autowired
    private ConsultRepository consultRepository;

    @Cacheable(cacheNames = "topSpecialities", key = "#threshold.orElse(2)")
    public List<SpecialityPatientCountDTO> getSpecialitiesWithPatientCountAboveThreshold(Optional<Integer> threshold) {
        return consultRepository.findSpecialitiesWithPatientCountAboveThreshold(threshold.orElse(2));
    }
}
