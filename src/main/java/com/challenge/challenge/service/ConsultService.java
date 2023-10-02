package com.challenge.challenge.service;

import com.challenge.challenge.model.Consult;
import com.challenge.challenge.repository.ConsultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {
    @Autowired
    private ConsultRepository consultRepository;

    public Consult saveConsult(Consult consult) {
        return consultRepository.save(consult);
    }

    public List<Consult> getAllConsults() {
        return consultRepository.findAll();
    }
}
