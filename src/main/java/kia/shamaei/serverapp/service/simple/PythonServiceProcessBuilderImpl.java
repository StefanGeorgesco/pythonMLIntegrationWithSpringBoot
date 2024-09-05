package kia.shamaei.serverapp.service.simple;

import kia.shamaei.serverapp.service.simple.PythonService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Service class for making predictions using a Python script.
 */
@Service
public class PythonServiceProcessBuilderImpl implements PythonService {
    /**
     * Executes a Python script to predict a value based on the given input.
     *
     * @param input The input value for the prediction.
     * @return The predicted result as a double.
     */
    @Override
    public double predict(String input) {
        double result = 0;
        try {

            ProcessBuilder pb = new ProcessBuilder("python3", "train-model.py", input);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            if ((line = reader.readLine()) != null) {
                result = Double.parseDouble(line);
            } else {
                throw new RuntimeException("No output from Python script.");
            }
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
