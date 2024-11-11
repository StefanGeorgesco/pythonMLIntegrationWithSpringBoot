package kia.shamaei.serverapp.service.simple;

import kia.shamaei.serverapp.service.pretrainModel.PretrainModelServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Service class for making predictions using a Python script.
 */
@Service
@Profile("default")
public class PythonServiceProcessBuilderImpl implements PythonService {

    private static final Logger logger = LoggerFactory.getLogger(PretrainModelServiceImpl.class);

    /**
     * Executes a Python script to predict a value based on the given input.
     *
     * @param input The input value for the prediction.
     * @return The predicted result as a double.
     */
    @Override
    public double predict(String input) {
        logger.info("making prediction with service {} and input: {}", getClass().getSimpleName(), input);
        double result = 0;
        try {

            ProcessBuilder pb = new ProcessBuilder("python", "make_prediction.py", input)
                    .redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            if ((line = reader.readLine()) != null) {
                result = Double.parseDouble(line.trim());
            } else {
                System.err.println("No output from Python script.");
            }
            process.waitFor();
        } catch (Exception e) {
            logger.error("Python script exited with code: {}", e.getMessage());
        }
        return result;
    }
}
