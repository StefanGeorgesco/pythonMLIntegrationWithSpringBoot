package kia.shamaei.serverapp.controller.pretrainModel;


import kia.shamaei.serverapp.service.pretrainModel.PretrainModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/vgg")
public class PretrainModelController {
    private static final long MIN_SIZE = 10 * 1024; // 10KB in bytes
    private static final long MAX_SIZE = 74 * 1024; // 74KB in bytes
    private final PretrainModelService pretrainModelService;

    public PretrainModelController(PretrainModelService pretrainModelService) {
        this.pretrainModelService = pretrainModelService;
    }

    @PostMapping("/image")
    public ResponseEntity<String> getImage(@RequestParam("image") MultipartFile image) throws IOException {
        if (image.isEmpty() || image.getSize() < MIN_SIZE || image.getSize() > MAX_SIZE) {
            return ResponseEntity.badRequest().body("Image size must be between 10KB and 74KB");
        }
        String response = pretrainModelService.recogniseImage(image.getBytes() ,"pretrain-model-resnet.py" );
        return ResponseEntity.ok(response);
    }
}
