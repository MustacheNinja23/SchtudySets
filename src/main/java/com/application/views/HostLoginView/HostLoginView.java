package com.application.views.HostLoginView;

import com.application.views.MainLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Host Login")
@Route(value = "host", layout = MainLayout.class)
public class HostLoginView extends VerticalLayout {
    public HostLoginView() {
        add(new TextField("Host Login"));
    }
}
