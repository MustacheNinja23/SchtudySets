package com.application.views.backend.utils;

import com.github.javaparser.quality.NotNull;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.BrowserWindowResizeEvent;

/*
    Custom utility class that uses the current session window
    to retrieve precise sizes/locations
*/
public class CurrentPageDimensions {
    private static int width;
    private static int height;

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static void update() {
        UI.getCurrent().getPage().retrieveExtendedClientDetails(e -> {
            height = e.getWindowInnerHeight();
            width = e.getWindowInnerWidth();
        });
    }

    public static void update(BrowserWindowResizeEvent event) {
        height = event.getHeight();
        width = event.getWidth();
    }


    //converts pixel size measurements of the element to integers
    //  this requires removing all characters from the decimal point on
    public static int getComponentWidth(@NotNull HasSize component) {
        StringBuilder tempWidthString = new StringBuilder();
        for (char c : component.getWidth().toCharArray()) {
            if (c == 46) break;
            tempWidthString.append(c);
        }
        return Integer.parseInt(tempWidthString.toString());
    }

    public static int getComponentHeight(@NotNull HasSize component) {
        StringBuilder tempHeightString = new StringBuilder();
        for (char c : component.getHeight().toCharArray()) {
            if (c == 46) break;
            tempHeightString.append(c);
        }
        return Integer.parseInt(tempHeightString.toString());
    }
}
