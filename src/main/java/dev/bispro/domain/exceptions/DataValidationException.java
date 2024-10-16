package dev.bispro.domain.exceptions;

public class DataValidationException extends RuntimeException{

    private DataValidationException(String message){
        super(message);
    }

    public static DataValidationException forInvalidInput(String message) {
        return new DataValidationException(message);
    }

}
