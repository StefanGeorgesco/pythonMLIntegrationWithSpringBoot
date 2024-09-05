package kia.shamaei.serverapp.service.pretrainModel;


import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.tensorflow.SavedModelBundle;
import org.tensorflow.Tensor;

import java.io.*;

@Service
@Log4j2
public class PretrainModelServiceImpl implements PretrainModelService {
    private SavedModelBundle model;

    @Override
    public String recogniseImage(byte[] nameOfFile , String nameOfModel) {
        model = SavedModelBundle.load("resnet.h5", "serve");
        Tensor<Float> input = Tensor.create(nameOfFile, Float.class);
        Tensor<Float> output = model
                .session()
                .runner()
                .feed("input", input)
                .fetch("output")
                .run()
                .get(0)
                .expect(Float.class);

        log.info("this is out put {} " , output.toString() );
        return "rrr";
    }

    /**
     *    public float[] predict(float[] input) {
     *         Tensor<Float> inputTensor = Tensor.create(input, Float.class);
     *         Tensor<Float> outputTensor = model.session().runner()
     *                 .feed("input", inputTensor)
     *                 .fetch("output")
     *                 .run()
     *                 .get(0)
     *                 .expect(Float.class);
     *
     *         float[] output = new float[outputTensor.shape()[1]];
     *         outputTensor.copyTo(output);
     *         return output;
     *     }
     * @param image
     * @return
     */

    @Override
    public String savedTempImage(byte[] image) {
            String tempFileName = "image.jpg";
            // Save the image to a temporary file
            File tempFile = new File(tempFileName);
            try (OutputStream os = new FileOutputStream(tempFile)) {
                os.write(image);
            }catch (Exception e){
                e.printStackTrace();
            }
            return tempFileName;
    }
}
