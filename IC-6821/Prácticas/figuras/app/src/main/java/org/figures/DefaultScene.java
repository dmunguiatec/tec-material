package org.figures;

import java.util.Collection;

public final class DefaultScene implements Scene {

    private final Collection<Figure> figures;

    public DefaultScene(final Collection<Figure> figures) {
        this.figures = figures;
    }


    @Override
    public void draw(Canvas canvas) {
        for (Figure figure : figures) {
            figure.draw(canvas);
        }
    }
}
