package dev.bispro.services.exceptions;

public class ServiceLayerException extends RuntimeException {

    public ServiceLayerException(String message) {
        super(message);
    }

    public static ServiceLayerException forInvalidArgument(String message) {
        return new ServiceLayerException(message);
    }

    public static ServiceLayerException forUpdateError(String message) {
        return new ServiceLayerException(message);
    }

    public static ServiceLayerException notFound(String message) {
        return new ServiceLayerException(message);
    }

    public static ServiceLayerException forDeleteError(String message) {
        return new ServiceLayerException(message);
    }

    public static ServiceLayerException forGetError(String message) {
        return new ServiceLayerException(message);
    }

    public static ServiceLayerException forCreateError(String message) {
        return new ServiceLayerException(message);
    }
}
