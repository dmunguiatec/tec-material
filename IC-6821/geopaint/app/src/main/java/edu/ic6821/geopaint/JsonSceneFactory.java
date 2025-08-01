package edu.ic6821.geopaint;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ic6821.geopaint.shapes.Circle;
import edu.ic6821.geopaint.shapes.Color;
import edu.ic6821.geopaint.shapes.Ellipse;
import edu.ic6821.geopaint.shapes.Point;
import edu.ic6821.geopaint.shapes.Rectangle;
import edu.ic6821.geopaint.shapes.Shape;
import edu.ic6821.geopaint.shapes.Triangle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public final class JsonSceneFactory {

    @JsonTypeInfo(
            use = JsonTypeInfo.Id.NAME,
            include = JsonTypeInfo.As.PROPERTY,
            property = "type"
    )
    @JsonSubTypes({
            @JsonSubTypes.Type(value = TriangleDTO.class, name = "triangle"),
            @JsonSubTypes.Type(value = CircleDTO.class, name = "circle"),
            @JsonSubTypes.Type(value = RectangleDTO.class, name = "rectangle"),
            @JsonSubTypes.Type(value = EllipseDTO.class, name = "ellipse")
    })
    public sealed interface ShapeDTO permits
            TriangleDTO, RectangleDTO, CircleDTO, EllipseDTO {
    }

    private record TriangleDTO(List<List<Integer>> vertices, String color) implements ShapeDTO {
    }

    private record RectangleDTO(List<Integer> position, int width, int height, String color) implements ShapeDTO {
    }

    private record CircleDTO(List<Integer> center, int radius, String color) implements ShapeDTO {
    }

    private record EllipseDTO(List<Integer> center, int width, int height, String color) implements ShapeDTO {
    }

    private final InputStream file;

    public JsonSceneFactory(final InputStream file) {
        this.file = file;
    }

    public Scene asScene() {
        try {
            final List<Shape> shapes = new ArrayList<>();

            final ObjectMapper mapper = new ObjectMapper();
            final List<ShapeDTO> objects = mapper.readValue(this.file, new TypeReference<>() {
            });

            for (ShapeDTO object : objects) {
                switch (object) {
                    case TriangleDTO triangleDTO:
                        shapes.add(createTriangle(triangleDTO));
                        break;
                    case CircleDTO circleDTO:
                        shapes.add(createCircle(circleDTO));
                        break;
                    case EllipseDTO ellipseDTO:
                        shapes.add(createEllipse(ellipseDTO));
                        break;
                    case RectangleDTO rectangleDTO:
                        shapes.add(createRectangle(rectangleDTO));
                        break;
                }
            }

            return new ShapeCollectionScene(shapes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Shape createRectangle(final RectangleDTO rectangleDTO) {
        return new Rectangle(
                new Point(rectangleDTO.position().getFirst(), rectangleDTO.position().getLast()),
                rectangleDTO.width(),
                rectangleDTO.height(),
                Color.from(rectangleDTO.color()).orElseThrow()
        );
    }

    private Shape createEllipse(final EllipseDTO ellipseDTO) {
        return new Ellipse(
                new Point(ellipseDTO.center().getFirst(), ellipseDTO.center().getLast()),
                ellipseDTO.width(),
                ellipseDTO.height(),
                Color.from(ellipseDTO.color()).orElseThrow()
        );
    }

    private Shape createCircle(final CircleDTO circleDTO) {
        System.out.println("circleDTO = " + circleDTO);
        return new Circle(
                new Point(circleDTO.center().getFirst(), circleDTO.center().getLast()),
                circleDTO.radius(),
                Color.from(circleDTO.color()).orElseThrow()
        );
    }

    private Shape createTriangle(final TriangleDTO object) {
        final List<Point> points = new ArrayList<>();
        for (List<Integer> vertex : object.vertices()) {
            points.add(new Point(vertex.get(0), vertex.get(1)));
        }

        return new Triangle(
                points.get(0),
                points.get(1),
                points.get(2),
                Color.from(object.color()).orElseThrow()
        );
    }
}
