package com.application.views.user_views;

import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.shared.Registration;
import org.vaadin.pekkam.Canvas;
import org.vaadin.pekkam.CanvasRenderingContext2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

// This file extends the Canvas Addon for Vaadin (https://vaadin.com/directory/component/canvas-java) to draw shapes/lines
// The Canvas class calls javascript methods to interact with the rendering context
public class VectorCanvas extends Canvas {
    private final ArrayList<VectorAreaShape> shapes = new ArrayList<>();
    private final CanvasRenderingContext2D context;
    private String brushType;
    private Registration moveListener;
    private boolean isHolding = false, hasCreated = false, isDrawing = false;

    public VectorCanvas(int width, int height) {
        super(width, height);
        this.setWidth(width, Unit.PIXELS);
        this.setHeight(height, Unit.PIXELS);
        context = super.getContext();

        brushType = "free";

        this.addMouseDownListener(down -> {
            if (!isDrawing) {
                if (!brushType.equals("move") && brushType.equals("free")) {
                    context.beginPath();
                    context.moveTo(down.getOffsetX(), down.getOffsetY());
                    moveListener = this.addMouseMoveListener(move -> {
                        context.lineTo(move.getOffsetX(), move.getOffsetY());
                        context.stroke();
                    });
                    isDrawing = true;
                } else if (!brushType.equals("move") && !hasCreated) {
                    shapes.add(new VectorAreaShape(brushType, down.getOffsetX(), down.getOffsetY()));
                    hasCreated = true;
                }
            }
        });

        this.addMouseUpListener(up -> {
            if (!brushType.equals("move") && brushType.equals("free")) {
                moveListener.remove();
                isDrawing = false;
            } else if (!brushType.equals("move")) {
                shapes.getLast().to(up.getOffsetX(), up.getOffsetY());
                this.clear();
                this.drawAll();
                hasCreated = false;
            }
        });
    }

    private void drawAll() {
        clear();
        for (int i = 0; i < shapes.size(); i++) {
            draw(shapes.get(i), i);
            for (Point point : shapes.get(i).points) {
                drawDraggablePoint(point);
            }
        }
    }

    private void draw(VectorAreaShape shape, int i) {
        Point start = shape.points.getFirst();
        Point end = shape.points.getLast();

        switch (shape.shapeType) {
            case "vec":
                context.beginPath();
                context.moveTo(start.x, start.y);
                context.lineTo(end.x, end.y);

                context.moveTo(end.x, end.y);

                double angle = shapes.get(i).getVecAngle();
                if (end.y < start.y) angle = -angle;
                double changeBy = Math.PI * 0.75;

                context.lineTo(
                        end.x + (10 * (Math.cos(angle + changeBy))),
                        end.y + (10 * (Math.sin(angle + changeBy)))
                );
                context.moveTo(end.x, end.y);
                context.lineTo(
                        end.x + (10 * (Math.cos(angle - changeBy))),
                        end.y + (10 * (Math.sin(angle - changeBy)))
                );
                context.closePath();
                context.stroke();

                break;
            case "rect":
                context.beginPath();
                context.moveTo(start.x, start.y);
                context.rect(start.x, start.y, end.x - start.x, end.y - start.y);
                context.closePath();
                context.stroke();

                break;
        }
    }

    private void drawDraggablePoint(Point point) {
        context.beginPath();
        context.arc(point.x, point.y, 1, 0, 2 * Math.PI, true);
        context.arc(point.x, point.y, 3, 0, 2 * Math.PI, true);
        context.closePath();
        context.stroke();

        this.addMouseDownListener(down -> {
            if (!isHolding && brushType.equals("move") && down.getOffsetX() >= point.x - 2 && down.getOffsetX() <= point.x + 2 && down.getOffsetY() >= point.y - 2 && down.getOffsetY() <= point.y + 2) {
                System.out.println("drawDraggablePoint");
                moveListener = this.addMouseMoveListener(move -> {
                    point.setLocation(move.getOffsetX(), move.getOffsetY());
                    toString();
                    this.clear();
                    this.drawAll();
                });
                this.addMouseUpListener(up -> {
                    point.setLocation(up.getOffsetX(), up.getOffsetY());
                    this.clear();
                    this.drawAll();
                    moveListener.remove();
                    isHolding = false;
                });
                isHolding = true;
            }
        });
    }

    public void setBrushWidth(double d) {
        context.setLineWidth(d);
    }

    public void setBrushType(String type) {
        this.brushType = type;
    }

    public void clear() {
        context.clearRect(0, 0, CurrentPageDimensions.getComponentWidth(this), CurrentPageDimensions.getComponentHeight(this));
    }

    public void fullClear() {
        clear();
        shapes.clear();
    }

    public ArrayList<VectorAreaShape> getShapes() {
        return shapes;
    }

    // Utility class for logging & tracking shapes
    static class VectorAreaShape {
        private final String shapeType;
        private final Vector<Point> points = new Vector<>();

        public VectorAreaShape(String type, int startX, int startY) {
            this.shapeType = type;
            points.add(new Point(startX, startY));
        }

        public void to(int endX, int endY) {
            points.add(new Point(endX, endY));
        }

        // find the dot product of the vector with the unit vector i (1,0)
        public double getVecAngle() {
            Vector<Double> vec = new Vector<>();
            vec.add((double) (points.getLast().x - points.getFirst().x));
            vec.add((double) (points.getLast().y - points.getFirst().y));
            return Math.acos(vec.getFirst() / (Math.sqrt(Math.pow(vec.getFirst(), 2) + Math.pow(vec.getLast(), 2))));
        }

        public String toString() {
            points.forEach(point -> System.out.println(point.x + " " + point.y));
            System.out.println("\n");
            return "";
        }
    }
}
