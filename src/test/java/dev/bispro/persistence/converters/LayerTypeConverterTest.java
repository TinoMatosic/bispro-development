package dev.bispro.persistence.converters;

import dev.bispro.domain.LayerType;
import dev.bispro.domain.Plan;
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
class LayerTypeConverterTest {

    private final LayerTypeConverter converter = new LayerTypeConverter();

    @Nested
    class enum_instance_to_db_value {
        @Test
        void can_convert_enum_instance() {
            assertThat(converter.convertToDatabaseColumn(LayerType.AREA)).isEqualTo('A');
            assertThat(converter.convertToDatabaseColumn(LayerType.TABLE)).isEqualTo('T');
            assertThat(converter.convertToDatabaseColumn(LayerType.TEXT)).isEqualTo('X');
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
        void can_convert_valid_db_value_to_enum_instance(Character literal, LayerType layerType) {
            assertThat(converter.convertToEntityAttribute(literal)).isEqualTo(layerType);
        }

        static Stream<Arguments> can_convert_valid_db_value_to_enum_instance() {
            return Stream.of(
                    Arguments.of('A', LayerType.AREA),
                    Arguments.of('T', LayerType.TABLE),
                    Arguments.of('X', LayerType.TEXT)
            );
        }

        @Test
        void can_convert_null(){
            assertThat(converter.convertToEntityAttribute(null)).isNull();
        }
    }
}