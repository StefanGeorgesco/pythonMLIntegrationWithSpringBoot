package kia.shamaei.serverapp.service.simple;

/**
 * Service class for making predictions using a Python script.
 */
public interface PythonService {
    public double predict(String input);
}
