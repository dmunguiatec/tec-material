package edu.ic6821.geopaint.shapes;

import org.jspecify.annotations.NonNull;

import java.awt.Graphics2D;

public final class Ellipse implements Shape {

    private final Point center;
    private final int radiusX;
    private final int radiusY;
    private final Color color;

    public Ellipse(
            @NonNull final Point center,
            final int radiusX,
            final int radiusY,
            @NonNull final Color color) {
        this.center = center;
        this.radiusX = radiusX;
        this.radiusY = radiusY;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(java.awt.Color.decode(this.color.hexCode()));
        graphics.fillOval(
                this.center.x(),
                this.center.y(),
                this.radiusX,
                this.radiusY
        );
    }
}
