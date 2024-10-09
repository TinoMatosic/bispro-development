package dev.bispro.persistence.converter;

import dev.bispro.domain.Role;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, Character> {

    public static final String COLUMN_DEFINITION = "enum ('A','P','S')";

    @Override
    public Character convertToDatabaseColumn(Role role) {
        return switch (role){
            case ADMIN -> 'A';
            case USER -> 'U';
            case LOCKED -> 'L';
            case null, default -> null;
        };
    }

    @Override
    public Role convertToEntityAttribute(Character dbData) {
        return switch (dbData) {
            case 'A' -> Role.ADMIN;
            case 'U' -> Role.USER;
            case 'L' -> Role.LOCKED;
            case null, default -> throw new RuntimeException("Data quality - to be fixed!");
        };
    }
}
