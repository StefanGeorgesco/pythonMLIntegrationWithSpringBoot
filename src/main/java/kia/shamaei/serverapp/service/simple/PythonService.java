package kia.shamaei.serverapp.service.simple;

/**
 * Service class for making predictions using a Python script.
 */
public interface PythonService {
    double predict(String input);
}
