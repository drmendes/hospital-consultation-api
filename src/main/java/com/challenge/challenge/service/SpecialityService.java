package com.challenge.challenge.service;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.model.Speciality;
import com.challenge.challenge.repository.ConsultRepository;
import com.challenge.challenge.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpecialityService {

    @Autowired
    private SpecialityRepository specialityRepository;

    public List<Speciality> getTopSpecialties(int threshold) {

        List<Speciality> allSpecialties = specialityRepository.findAll();
        return allSpecialties.stream()
                //.filter(speciality -> speciality.getConsults().size() > threshold)
                .collect(Collectors.toList());
    }
}
