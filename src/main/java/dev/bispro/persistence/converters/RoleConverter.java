package dev.bispro.persistence.converters;

import dev.bispro.domain.Role;
import dev.bispro.persistence.exceptions.DataQualityException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Character> {

    public static final String VALID_VALUES = "'A','P','S'";
    public static final String COLUMN_DEFINITION = "enum ("+VALID_VALUES+")";

    @Override
    public Character convertToDatabaseColumn(Role role) {
        return switch (role){
            case ADMIN -> 'A';
            case USER -> 'U';
            case LOCKED -> 'L';
            case null -> null;
        };
    }

    @Override
    public Role convertToEntityAttribute(Character dbData) {
        return switch (dbData) {
            case 'A' -> Role.ADMIN;
            case 'U' -> Role.USER;
            case 'L' -> Role.LOCKED;
            case null -> null;
            default -> throw DataQualityException.forUnsupportedLiteral(dbData, Role.class, VALID_VALUES);
        };
    }
}
