package com.application.views.ViewContainer;

import com.application.views.HostLoginView.HostLoginView;
import com.application.views.HostStartGameView.HostStartGameView;
import com.application.views.MainLayout;
import com.application.views.QuestionView.QuestionView;
import com.application.views.UserLoginView.UserLoginView;
import com.application.views.WaitingView.WaitingView;
import com.application.views.backend.CurrentPageDimensions;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

@Route(value = "", layout = MainLayout.class)
public class ViewContainer extends VerticalLayout implements AfterNavigationObserver {
    static int rel = 0;

    private UserLoginView userLoginView = new UserLoginView();
    private HostLoginView hostLoginView = new HostLoginView();
    private QuestionView questionView = new QuestionView();
    private WaitingView waitingView = new WaitingView();
    private HostStartGameView hostStartGameView = new HostStartGameView();

    private String gameNumber = "";
    private String currentView = "";


    public ViewContainer() {
        UI.getCurrent().getSession().setAttribute("viewContainer", this);

        currentView = "userLoginView";

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            CurrentPageDimensions.update(e);
            changeToView(currentView);
        });
    }

    public String getGameNumber() {
        return gameNumber;
    }

    public void changeToView(String viewName) {
        UI.getCurrent().removeAll();
        currentView = viewName;

        switch (viewName) {
            case "userLoginView":
                userLoginView.createPage();
                UI.getCurrent().add(userLoginView);
                break;
            case "hostLoginView":
                hostLoginView.createPage();
                UI.getCurrent().add(hostLoginView);
                break;
            case "questionView":
                questionView.createPage();
                UI.getCurrent().add(questionView);
                break;
            case "waitingView":
                waitingView.createPage();
                UI.getCurrent().add(waitingView);
                break;
            case "hostStartGameView":
                hostStartGameView.createPage();
                UI.getCurrent().add(hostStartGameView);
                break;
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
