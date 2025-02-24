package com.application.views.user_views;

import com.application.views.backend.utils.AbsoluteLayout;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.vaadin.pekkam.Canvas;
import org.vaadin.pekkam.CanvasRenderingContext2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

// Uses the Canvas Addon for Vaadin; https://vaadin.com/directory/component/canvas-java
public class VectorFieldView extends AbsoluteLayout {
    private HorizontalLayout toolbar;
    private VectorCanvas canvas;
    private int width, height;

    public VectorFieldView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void createPage() {
        toolbar = new HorizontalLayout();
        toolbar.setWidth(width + "");
        toolbar.setHeight(height / 12 + "");
        toolbar.getStyle().set("background-color", "light-gray");
        toolbar.getStyle().set("border", "1px solid black");

        toolbar.add(new Button("Vector", event -> canvas.setBrushType("vec")));
        toolbar.add(new Button("Rectangle", event -> canvas.setBrushType("rect")));
        toolbar.add(new Button("Clear", event -> canvas.clear()));

        canvas = new VectorCanvas(width, height - Integer.parseInt(toolbar.getHeight()));
        canvas.getStyle().set("border", "1px solid black");

        this.add(toolbar, 0, 0);
        this.add(canvas, Integer.parseInt(toolbar.getHeight()), 0);
    }

    class VectorCanvas extends Canvas{
        private ArrayList<VectorAreaShape> shapes = new ArrayList<>();
        private CanvasRenderingContext2D context;
        private String brushType;

        private Point start;

        public VectorCanvas(int width, int height) {
            super(width, height);
            context = super.getContext();

            brushType = "vec";

            this.addMouseDownListener(event -> {
                start = new Point(event.getOffsetX(), event.getOffsetY());

                shapes.add(new VectorAreaShape(brushType, start.x, start.y));
            });
            this.addMouseUpListener(event -> {
                Point end = new Point(event.getOffsetX(), event.getOffsetY());

                switch (brushType) {
                    case "vec":
                        shapes.getLast().to(end.x, end.y);
                        context.beginPath();
                        context.moveTo(start.x, start.y);
                        context.lineTo(end.x, end.y);

                        context.moveTo(end.x, end.y);

                        double angle = shapes.getLast().getVecAngle();
                        if(end.y < start.y) angle = -angle;
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
                    case "rect": //TODO: fix rectangles
                        shapes.getLast().to(end.x, end.y);
                        context.beginPath();
                        context.moveTo(start.x, start.y);
                        context.rect(start.x, start.y, end.x - start.x, end.y - start.x);
                        context.closePath();
                        context.stroke();
                        break;
                }

                shapes.getLast().to(event.getOffsetX(), event.getOffsetY());
            });
        }

        public void setBrushType(String type) {
            this.brushType = type;
        }

        public void clear() {
            context.clearRect(0, 0, width, height);
        }

        // Utility class for logging & tracking shapes
        static class VectorAreaShape {
            private String brushType;
            Vector<Point> points = new Vector<>();
            public VectorAreaShape(String type, int startX, int startY) {
                this.brushType = type;
                points.add(new Point(startX, startY));
            }

            public void to(int endX, int endY) {
                switch(brushType){
                    case "vec":
                        points.add(new Point(endX, endY));
                        break;
                    case "rect":
                        points.add(new Point(endX, endY));
                        points.add(1, new Point(points.getLast().x, points.getFirst().y));
                        points.add(2, new Point(points.getFirst().x, points.getLast().y));
                        break;
                }
            }

            // find the dot product of the vector with the unit vector i (1,0)
            public double getVecAngle() {
                Vector<Double> vec = new Vector<>();
                vec.add((double)(points.getLast().x - points.getFirst().x));
                vec.add((double)(points.getLast().y - points.getFirst().y));
                return Math.acos(vec.getFirst() / (Math.sqrt(Math.pow(vec.getFirst(), 2) + Math.pow(vec.getLast(), 2))));
            }

            public Vector<Point> getPoints() {
                return points;
            }
        }
    }
}

