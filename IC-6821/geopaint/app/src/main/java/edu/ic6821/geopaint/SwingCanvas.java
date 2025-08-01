package edu.ic6821.geopaint;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

public final class SwingCanvas extends JPanel {

    private static final long serialVersionUID = 1L;

    private final transient Scene scene;

    public SwingCanvas(final Scene scene) {
        this.scene = scene;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D graphics = (Graphics2D) g;
        this.scene.draw(graphics);
    }

}
