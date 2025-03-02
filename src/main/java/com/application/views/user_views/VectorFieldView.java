package com.application.views.user_views;

import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.RangeInput;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class VectorFieldView extends AbsoluteLayout {
    private final int width;
    private final int height;
    private VectorCanvas canvas;
    private RangeInput widthSlider;

    public VectorFieldView(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void createPage() {
        AbsoluteLayout toolbar = new AbsoluteLayout();
        toolbar.getStyle().set("border", "1px solid black");
        toolbar.getStyle().setPadding("3");
        toolbar.setHeight(CurrentPageDimensions.getHeight() / 12 + "");

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.add(new Button("Pen", _ -> canvas.setBrushType("free")));
        buttons.add(new Button("Vector", _ -> canvas.setBrushType("vec")));
        buttons.add(new Button("Rectangle", _ -> canvas.setBrushType("rect")));
        buttons.add(new Button("Move", _ -> canvas.setBrushType("move")));
        toolbar.add(buttons);

        widthSlider = new RangeInput();
        widthSlider.setMax(10.0);
        widthSlider.setMin(1.0);
        widthSlider.setStep(1.0);
        widthSlider.setValue(2.0);
        widthSlider.addValueChangeListener(_ -> canvas.setBrushWidth(widthSlider.getValue()));
        HorizontalLayout slider = new HorizontalLayout(new Text("Brush width:"), widthSlider);
        slider.add(new Button("Clear", _ -> canvas.fullClear()));
        toolbar.add(slider);

        canvas = new VectorCanvas(width, height - Integer.parseInt(toolbar.getHeight()));
        canvas.getStyle().set("border", "1px solid black");

        this.add(toolbar, 0, 0);
        this.add(canvas, Integer.parseInt(toolbar.getHeight()), 0);
    }

    public VectorCanvas getCanvas() {
        return canvas;
    }
}
