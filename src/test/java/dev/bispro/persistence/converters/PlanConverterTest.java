package dev.bispro.persistence.converters;

import dev.bispro.domain.Plan;
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
class PlanConverterTest {

    private final PlanConverter converter = new PlanConverter();

    @Nested
    class enum_instance_to_db_value {
        @Test
        void can_convert_enum_instance() {
            assertThat(converter.convertToDatabaseColumn(Plan.FREE)).isEqualTo('F');
            assertThat(converter.convertToDatabaseColumn(Plan.PRO)).isEqualTo('P');
            assertThat(converter.convertToDatabaseColumn(Plan.PREMIUM)).isEqualTo('M');
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
        void can_convert_valid_db_value_to_enum_instance(Character literal, Plan plan) {
            assertThat(converter.convertToEntityAttribute(literal)).isEqualTo(plan);
        }

        static Stream<Arguments> can_convert_valid_db_value_to_enum_instance() {
            return Stream.of(
                    Arguments.of('F', Plan.FREE),
                    Arguments.of('P', Plan.PRO),
                    Arguments.of('M', Plan.PREMIUM)
            );
        }

        @Test
        void can_convert_null(){
            assertThat(converter.convertToEntityAttribute(null)).isNull();
        }
    }
}