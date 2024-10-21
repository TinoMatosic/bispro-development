package dev.bispro.persistence.converters;

import dev.bispro.domain.LayerType;
import dev.bispro.domain.Role;
import dev.bispro.persistence.exceptions.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LayerTypeConverter implements AttributeConverter<LayerType, Character> {

    public static final String VALID_VALUES = "'A','T','X'";
    public static final String COLUMN_DEFINITION = "enum ("+VALID_VALUES+")";

    @Override
    public Character convertToDatabaseColumn(LayerType layerType) {
        return switch (layerType){
            case AREA-> 'A';
            case TABLE -> 'T';
            case TEXT -> 'X';
            case null -> null;
        };
    }

    @Override
    public LayerType convertToEntityAttribute(Character dbData) {
        return switch (dbData) {
            case 'A' -> LayerType.AREA;
            case 'U' -> LayerType.TABLE;
            case 'L' -> LayerType.TEXT;
            case null -> null;
            default -> throw DataQualityException.forUnsupportedLiteral(dbData, LayerType.class, VALID_VALUES);
        };
    }
}
