package com.application.views.ViewContainer;

import com.application.views.HostLoginView.HostLoginView;
import com.application.views.HostStartGameView.HostStartGameView;
import com.application.views.MainLayout;
import com.application.views.QuestionView.QuestionView;
import com.application.views.UserLoginView.UserLoginView;
import com.application.views.WaitingView.WaitingView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "", layout = MainLayout.class)
public class ViewContainer extends VerticalLayout implements AfterNavigationObserver {
    static int rel = 0;

    UserLoginView userLoginView = new UserLoginView();
    HostLoginView hostLoginView = new HostLoginView();
    QuestionView questionView = new QuestionView();
    WaitingView waitingView = new WaitingView();
    HostStartGameView hostStartGameView = new HostStartGameView();

    private String gameNumber = "";


    public ViewContainer() {
        UI.getCurrent().getSession().setAttribute("viewContainer", this);
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public void changeToView(String viewName) {
        UI.getCurrent().removeAll();

        switch (viewName) {
            case "userLoginView":
                UI.getCurrent().add(userLoginView);
            case "hostLoginView":
                UI.getCurrent().add(hostLoginView);
            case "questionView":
                UI.getCurrent().add(questionView);
            case "waitingView":
                UI.getCurrent().add(waitingView);
            case "hostStartGameView":
                UI.getCurrent().add(hostStartGameView);
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        add(userLoginView);
        if(rel == 0) {
            this.removeAll();
            UI.getCurrent().getPage().reload();
            rel++;
        }
    }
}
