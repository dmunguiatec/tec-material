package edu.ic6821.geopaint;

import edu.ic6821.geopaint.shapes.Shape;
import org.jspecify.annotations.NonNull;

import java.awt.Graphics2D;
import java.util.Collection;

public final class ShapeCollectionScene implements Scene {

    private final Collection<Shape> shapes;

    public ShapeCollectionScene(@NonNull final Collection<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public void draw(final Graphics2D graphics) {
        for (Shape shape : shapes) {
            shape.draw(graphics);
        }
    }
}
