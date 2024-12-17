package com.application.views;

import com.application.views.HostLoginView.HostLoginView;
import com.application.views.HostStartGameView.HostStartGameView;
import com.application.views.QuestionView.QuestionView;
import com.application.views.UserLoginView.UserLoginView;
import com.application.views.WaitingView.WaitingView;
import com.application.views.backend.AbsoluteLayout;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Layout
public class MainLayout extends VerticalLayout implements RouterLayout {
    public MainLayout() {}
}
