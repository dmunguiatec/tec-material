package org.figures;

public final class Square implements Figure {
    private final Colorable colorable;
    private final Positionable positionable;
    private final double side;

    public Square(final Colorable colorable, final Positionable positionable, final double side) {
        this.colorable = colorable;
        this.positionable = positionable;
        this.side = side;
    }

    @Override
    public Color border() {
        return colorable.border();
    }

    @Override
    public Color fill() {
        return colorable.fill();
    }

    @Override
    public void draw(Canvas canvas) {
        // dibujar el cuadrado aquí usando el canvas
    }

    @Override
    public Point position() {
        return positionable.position();
    }
}
