package com.application.views.backend;

import com.vaadin.flow.component.UI;

public class CurrentPageDimensions {
    private static int width;
    private static int height;

    public static int getHeight() {
        UI.getCurrent().getPage().retrieveExtendedClientDetails(evt -> {
            height =  evt.getWindowInnerHeight();
        });
        return height;
    }

    public static int getWidth() {
        UI.getCurrent().getPage().retrieveExtendedClientDetails(evt -> {
            width = evt.getWindowInnerWidth();
        });
        return width;
    }
}
