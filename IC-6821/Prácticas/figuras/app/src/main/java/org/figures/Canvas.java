package org.figures;

public interface Canvas {
    void drawPoint(Point point, Point position);
    void drawSegment(Point pointA, Point pointB);
    void drawArc(double radius, Point position);
}
