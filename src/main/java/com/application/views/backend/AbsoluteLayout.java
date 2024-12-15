package com.application.views.backend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

public class AbsoluteLayout extends Div {  //Credit to Peppe @ https://github.com/Peppe/absolute-layout-demo

    public AbsoluteLayout() {
        getElement().getStyle().set("position", "relative");
    }

    public void add(Component component, int top, int left) {
        add(component);
        component.getElement().getStyle().set("position", "absolute");
        component.getElement().getStyle().set("top", top + "px");
        component.getElement().getStyle().set("left", left + "px");
    }
}
