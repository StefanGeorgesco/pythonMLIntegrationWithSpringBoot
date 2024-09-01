package kia.shamaei.serverapp.controller;


import kia.shamaei.serverapp.controller.dto.PredictResponseDto;
import kia.shamaei.serverapp.service.PythonService;
import kia.shamaei.serverapp.service.PythonServiceApacheCommonImpl;
import kia.shamaei.serverapp.service.PythonServiceProcessBuilderImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predict")
public class PredictSimpleController {

    private final PythonService pythonService;

    public PredictSimpleController(PythonServiceProcessBuilderImpl pythonService) {
        this.pythonService = pythonService;
    }

    @GetMapping("/{input}")
    public double predict(@PathVariable String  input) {
        System.out.println("log");
        return pythonService.predict(input); // Predict using the Python model
    }


}
