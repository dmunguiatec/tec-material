package org.figures;

public final class Triangle implements Figure {

    private final Colorable colorable;
    private final Positionable positionable;
    private final Double side;

    public Triangle(final Colorable colorable, final Positionable positionable, final Double side) {
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
        // dibujar el triángulo
    }

    @Override
    public Point position() {
        return positionable.position();
    }
}
