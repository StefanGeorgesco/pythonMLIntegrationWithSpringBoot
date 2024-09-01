package kia.shamaei.serverapp.service;

import kia.shamaei.serverapp.controller.dto.PredictResponseDto;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Service class for making predictions using a Python script.
 */
public interface PythonService {
    public double predict(String input);
}
