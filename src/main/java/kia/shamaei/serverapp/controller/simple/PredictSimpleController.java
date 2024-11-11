package kia.shamaei.serverapp.controller.simple;


import kia.shamaei.serverapp.controller.simple.dto.PredictQueryDto;
import kia.shamaei.serverapp.controller.simple.dto.PredictResponseDto;
import kia.shamaei.serverapp.service.simple.PythonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/predict")
public class PredictSimpleController {

    private final PythonService pythonService;

    public PredictSimpleController(PythonService pythonService) {
        this.pythonService = pythonService;
    }

    @PostMapping
    public ResponseEntity<PredictResponseDto> predict(@RequestBody PredictQueryDto predictQueryDto) {
        String input = predictQueryDto.input();
        return ResponseEntity.ok(new PredictResponseDto(Double.parseDouble(input), pythonService.predict(input)));
    }
}
