package kia.shamaei.serverapp.service.simple;

import kia.shamaei.serverapp.service.simple.PythonService;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

/**
 * Service class for making predictions using a Python script.
 */
@Service
public class PythonServiceApacheCommonImpl implements PythonService {
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
            // Adjust the command line to include input if necessary
            String line = "python3 train-model.py " + input; // Assuming getValue() retrieves the necessary input
            CommandLine cmdLine = CommandLine.parse(line);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setStreamHandler(streamHandler);
            int exitCode = executor.execute(cmdLine);

            // Check if the exit code is successful
            if (exitCode == 0) {
                // Parse the output to get the result
                String output = outputStream.toString().trim();
                result = Double.parseDouble(output); // Make sure the output is a valid double
            } else {
                System.err.println("Python script exited with code: " + exitCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
