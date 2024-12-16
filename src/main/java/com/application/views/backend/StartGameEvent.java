package com.application.views.backend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class StartGameEvent extends ComponentEvent<Component> {
    public StartGameEvent(Component source, boolean fromClient) {
        super(source, fromClient);
    }
}
