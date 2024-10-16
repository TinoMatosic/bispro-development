package dev.bispro.persistence.exceptions;

public class DataQualityException extends RuntimeException{

    private DataQualityException(String message, Character literal, Class<? extends Enum<?>> enumClass, String validValues){
        super(message);
    }

    public static DataQualityException forUnsupportedLiteral(Character literal, Class<? extends Enum<?>> enumClass, String validValues){
        String msg = "Unsupported enum literal '%c' for class '%s' in the DC! - Valid values: [%s]".formatted(literal, enumClass, validValues);
        return new DataQualityException(msg, literal, enumClass, validValues);
    }

}
