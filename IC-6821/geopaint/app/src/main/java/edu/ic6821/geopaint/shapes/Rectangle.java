package edu.ic6821.geopaint.shapes;

import org.jspecify.annotations.NonNull;

import java.awt.Graphics2D;
import java.util.Objects;

public final class Rectangle implements Shape {

    private final Point position;
    private final int width;
    private final int height;
    private final Color color;

    public Rectangle(
            @NonNull final Point position,
            final int width,
            final int height,
            @NonNull final Color color) {
        this.position = Objects.requireNonNull(position);
        this.width = width;
        this.height = height;
        this.color = Objects.requireNonNull(color);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(java.awt.Color.decode(this.color.hexCode()));
        graphics.fillRect(
                this.position.x(),
                this.position.y(),
                this.width,
                this.height
        );
    }
}
