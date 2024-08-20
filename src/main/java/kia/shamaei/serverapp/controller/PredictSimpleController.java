package kia.shamaei.serverapp.controller;


import kia.shamaei.serverapp.service.PythonService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/predict")

public class PredictSimpleController {

    private final PythonService pythonService;


    public PredictSimpleController(PythonService pythonService) {
        this.pythonService = pythonService;
    }

    @PostMapping
    public double predict(@RequestBody double input) {
        return pythonService.predict(input); // Predict using the Python model
    }


}
