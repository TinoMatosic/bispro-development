package dev.bispro.persistence.converters;

import dev.bispro.domain.Role;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RoleConverterTest {

    private final RoleConverter converter = new RoleConverter();

    @Nested
    class enum_instance_to_db_value {
        @Test
        void can_convert_enum_instance() {
            assertThat(converter.convertToDatabaseColumn(Role.ADMIN)).isEqualTo('A');
            assertThat(converter.convertToDatabaseColumn(Role.LOCKED)).isEqualTo('L');
            assertThat(converter.convertToDatabaseColumn(Role.USER)).isEqualTo('U');
        }

        @Test
        void can_convert_null(){
            assertThat(converter.convertToDatabaseColumn(null)).isNull();
        }
    }

    @Nested
    class db_value_to_enum_instance {

        @ParameterizedTest
        @MethodSource
        void can_convert_valid_db_value_to_enum_instance(Character literal, Role role) {
            assertThat(converter.convertToEntityAttribute(literal)).isEqualTo(role);
        }

        static Stream<Arguments> can_convert_valid_db_value_to_enum_instance() {
            return Stream.of(
                    Arguments.of('A', Role.ADMIN),
                    Arguments.of('L', Role.LOCKED),
                    Arguments.of('U', Role.USER)
            );
        }

        @Test
        void can_convert_null(){
            assertThat(converter.convertToEntityAttribute(null)).isNull();
        }
    }
}