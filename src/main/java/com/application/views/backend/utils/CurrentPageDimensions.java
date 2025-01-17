package com.application.views.backend.utils;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.page.BrowserWindowResizeEvent;

/*
    Custom utility class that uses the current session window
    to retrieve precise sizes/locations
*/
public class CurrentPageDimensions{
    private static int width;
    private static int height;

    public static int getHeight() {
        return height;
    }

    public static int getWidth() {
        return width;
    }

    public static void update() {
        UI.getCurrent().getPage().retrieveExtendedClientDetails(e ->{
            height = e.getWindowInnerHeight();
            width = e.getWindowInnerWidth();
        });
    }

    public static void update(BrowserWindowResizeEvent event) {
        height = event.getHeight();
        width = event.getWidth();
    }
}
