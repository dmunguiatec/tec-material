package edu.ic6821.geopaint;

import javax.swing.JFrame;


public final class GeoPaint {

    public static final String JSON_FILE_SCENE = "/escena.json";
    public static final int FRAME_WIDTH = 800;
    public static final int FRAME_HEIGHT = 700;

    public static void main(String[] args) {
        final Scene scene = new JsonSceneFactory(GeoPaint.class.getResourceAsStream(JSON_FILE_SCENE)).asScene();
        showFrame(scene);
    }

    private static void showFrame(Scene scene) {
        final JFrame frame = new JFrame("Escena");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.add(new SwingCanvas(scene));
        frame.setVisible(true);
    }

    private GeoPaint() { }
}
