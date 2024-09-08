package org.figures;

public record Color(int red, int green, int blue) {

    private static final int HEXADECIMAL_RADIX = 16;
    private static final int RED_BEGIN_INDEX = 1;
    private static final int RED_END_INDEX = 3;
    private static final int GREEN_BEGIN_INDEX = 3;
    private static final int GREEN_END_INDEX = 5;
    private static final int BLUE_BEGIN_INDEX = 5;
    private static final int BLUE_END_INDEX = 7;

    public Color(final String hex) {
        this(
                Integer.parseInt(hex.substring(RED_BEGIN_INDEX, RED_END_INDEX), HEXADECIMAL_RADIX),
                Integer.parseInt(hex.substring(GREEN_BEGIN_INDEX, GREEN_END_INDEX), HEXADECIMAL_RADIX),
                Integer.parseInt(hex.substring(BLUE_BEGIN_INDEX, BLUE_END_INDEX), HEXADECIMAL_RADIX)
        );
    }

    public String toHex() {
        return String.format("#%02x;%02x;%02x;",
                this.red,
                this.green,
                this.blue);
    }
}
