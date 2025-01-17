package com.application.views.backend.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

/*
    Credit to Peppe @ https://github.com/Peppe/absolute-layout-demo

    Custom Layout class that allows for pixel-precise positioning of
    elements in the window

    NOTE: A Vaadin official AbsoluteLayout class existed in a previous version,
    but is not available in Vaadin 23 (used by this project)
*/
public class AbsoluteLayout extends Div {
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
