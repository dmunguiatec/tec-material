package edu.ic6821.geopaint.shapes;

import org.jspecify.annotations.NonNull;

import java.awt.Graphics2D;

public final class Circle implements Shape {

    private final Point center;
    private final int radius;
    private final Color color;

    public Circle(
            @NonNull final Point center,
            final int radius,
            @NonNull final Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(java.awt.Color.decode(this.color.hexCode()));
        graphics.fillOval(
                this.center.x(),
                this.center.y(),
                this.radius,
                this.radius
        );
    }
}
