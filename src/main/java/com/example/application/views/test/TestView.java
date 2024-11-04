package com.example.application.views.test;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility.Margin;
import java.awt.*;

@PageTitle("test")
@Route(value = "", layout = MainLayout.class)
public class TestView extends VerticalLayout {

    public TestView() {
        
    }

}
