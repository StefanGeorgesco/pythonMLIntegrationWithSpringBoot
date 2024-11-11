package kia.shamaei.serverapp.service.simple;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

/**
 * Service class for making predictions using a Python script.
 */
@Service
@Profile("apache-common")
public class PythonServiceApacheCommonImpl implements PythonService {

    private static final Logger logger = LoggerFactory.getLogger(PythonServiceApacheCommonImpl.class);

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
            String line = "python make_prediction.py " + input;
            CommandLine cmdLine = CommandLine.parse(line);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setStreamHandler(streamHandler);
            int exitCode = executor.execute(cmdLine);
            if (exitCode == 0) {
                String output = outputStream.toString().trim();
                result = Double.parseDouble(output);
            } else {
                System.err.println("Python script exited with code: " + exitCode);
            }
        } catch (Exception e) {
            logger.error("Python script exited with code: {}", e.getMessage());
        }
        return result;
    }
}
