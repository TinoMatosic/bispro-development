package dev.bispro.persistence.converters;

import dev.bispro.domain.Plan;
import dev.bispro.persistence.exceptions.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class PlanConverter implements AttributeConverter<Plan, Character> {

    public static final String VALID_VALUES = "'F','P','M'";
    public static final String COLUMN_DEFINITION = "enum ("+VALID_VALUES+")";

    @Override
    public Character convertToDatabaseColumn(Plan plan) {
        return switch (plan){
            case FREE -> 'F';
            case PRO -> 'P';
            case PREMIUM -> 'M';
            case null -> null;
        };
    }

    @Override
    public Plan convertToEntityAttribute(Character dbData) {
        return switch (dbData) {
            case 'F' -> Plan.FREE;
            case 'P' -> Plan.PRO;
            case 'M' -> Plan.PREMIUM;
            case null -> null;
            default -> throw DataQualityException.forUnsupportedLiteral(dbData, Plan.class, VALID_VALUES);
        };
    }
}
