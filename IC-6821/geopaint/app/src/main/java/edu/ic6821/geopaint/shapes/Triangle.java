package edu.ic6821.geopaint.shapes;

import org.jspecify.annotations.NonNull;

import java.awt.Graphics2D;
import java.util.Objects;

public final class Triangle implements Shape {

    private static final int N_VERTICES = 3;

    private final Point vertexA;
    private final Point vertexB;
    private final Point vertexC;
    private final Color color;

    public Triangle(
            @NonNull final Point vertexA,
            @NonNull final Point vertexB,
            @NonNull final Point vertexC,
            @NonNull final Color color) {
        this.vertexA = Objects.requireNonNull(vertexA);
        this.vertexB = Objects.requireNonNull(vertexB);
        this.vertexC = Objects.requireNonNull(vertexC);
        this.color = Objects.requireNonNull(color);
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setColor(java.awt.Color.decode(this.color.hexCode()));
        graphics.fillPolygon(
                new int[]{vertexA.x(), vertexB.x(), vertexC.x()},
                new int[]{vertexA.y(), vertexB.y(), vertexC.y()},
                N_VERTICES
        );
    }
}
