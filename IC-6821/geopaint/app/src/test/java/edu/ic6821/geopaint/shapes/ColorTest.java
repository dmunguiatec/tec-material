package edu.ic6821.geopaint.shapes;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ColorTest {

    @Test
    void constructColorWithValidHexCode() {
        // given
        final String hexCode = "#FF230A";

        // when
        final Optional<Color> color = Color.from(hexCode);

        // then
        assertTrue(color.isPresent());
        assertEquals(hexCode, color.get().hexCode());
    }

    @Test
    void constructColorWithNullHexCode() {
        // given
        final String hexCode = null;

        // when
        final Optional<Color> color = Color.from(hexCode);

        // then
        assertTrue(color.isEmpty());
    }

    @Test
    void constructColorWithInvalidHexCode() {
        // given
        final String hexCode = "hola mundo!";

        // when
        final Optional<Color> color = Color.from(hexCode);

        // then
        assertTrue(color.isEmpty());
    }



}
