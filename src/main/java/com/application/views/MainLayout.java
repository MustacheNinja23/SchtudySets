package com.application.views;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

/*
    **KEY FOR COMMENTS**
        * All class names use PascalCase
        * All variable names use camelCase
        * Each class has comments at the heading of the page to
          briefly explain its function
        * Capitalized words in a comment refer to a class of a
          type of class
*/

// Default Vaadin class, active container used is the ViewContainer class
@Layout
public class MainLayout extends VerticalLayout implements RouterLayout {
    public MainLayout() {}
}
