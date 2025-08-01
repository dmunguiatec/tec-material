package edu.ic6821.geopaint.shapes;

import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.regex.Pattern;

public final class Color {

    private static final Pattern HEX_COLOR_PATTERN =
            Pattern.compile("^#[A-Fa-f0-9]{6}");

    private final String hexCode;

    private Color(@NonNull final String hexCode) {
        this.hexCode = hexCode;
    }

    public static Optional<Color> from(@NonNull String hexCode) {
        if (hexCode == null || !HEX_COLOR_PATTERN.matcher(hexCode).matches()) {
            return Optional.empty();
        }

        return Optional.of(new Color(hexCode));
    }

    public String hexCode() {
        return this.hexCode;
    }

}
