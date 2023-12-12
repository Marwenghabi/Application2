package com.example.Application2.service;

import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public String performFakeCalculation(String input) {
       
        return "Result for " + input;
    }
}
