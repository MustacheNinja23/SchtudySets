package com.application.views;

import com.application.views.host_views.HostLeaderboardView;
import com.application.views.user_views.QuestionContainer;
import com.application.views.user_views.UserLoginView;
import com.application.views.user_views.WaitingView;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.application.views.host_views.HostLoginView;
import com.application.views.host_views.HostStartGameView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

/*
    The ViewContainer class is active and displayed at all times, and contains
    an instance of another View class within it

    Allows for seamless swapping of actively displayed view, skipping any
    otherwise necessary page-loading/reloading steps
*/
@Route(value = "", layout = MainLayout.class)
public class ViewContainer extends VerticalLayout implements AfterNavigationObserver {
    static int rel = 0; // for tracking reloads

    // Views
    private UserLoginView userLoginView;
    private HostLoginView hostLoginView;
    private QuestionContainer questionContainer;
    private WaitingView waitingView;
    private HostStartGameView hostStartGameView;
    private HostLeaderboardView hostLeaderboardView;

    //internal
    private String gameNumber = "";
    private String currentView = "";
    UI ui;

    // Adds ViewContainer instance to the current session
    public ViewContainer() {
        UI.getCurrent().getSession().setAttribute("viewContainer", this);

        currentView = "userLoginView";
        initialize();

        ui = UI.getCurrent();

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            CurrentPageDimensions.update(e);
            changeToView(currentView);
        });
    }

    // Creates all used class instances
    public void initialize() {
        userLoginView = new UserLoginView();
        hostLoginView = new HostLoginView();
        questionContainer = new QuestionContainer();
        waitingView = new WaitingView();
        hostStartGameView = new HostStartGameView();
        hostLeaderboardView = new HostLeaderboardView();
    }

    public UI getUi() {
        return UI.getCurrent();
    }

    public void setGameNumber(String g) {
        this.gameNumber = g;
    }

    public String getGameNumber() {
        return gameNumber;
    }

    // Swaps actively displayed View in the session's ViewContainer instance
    public void changeToView(String viewName) {
        currentView = viewName;

        // Locks current session to ensure thread safety
        ui.access(() -> {
            VaadinSession.getCurrent().getLockInstance().lock();
            ui.removeAll();

            // Swaps active View, not inactive Views/elements are ignored by Vaadin
            switch (viewName) {
                case "userLoginView":
                    userLoginView.createPage();
                    ui.add(userLoginView);
                    break;
                case "hostLoginView":
                    hostLoginView.createPage();
                    ui.add(hostLoginView);
                    break;
                case "questionView":
                    questionContainer.createPage();
                    ui.add(questionContainer);
                    break;
                case "waitingView":
                    waitingView.createPage();
                    ui.add(waitingView);
                    break;
                case "hostStartGameView":
                    hostStartGameView.createPage();
                    ui.add(hostStartGameView);
                    break;
                case "hostLeaderboardView":
                    hostLeaderboardView.createPage();
                    ui.add(hostStartGameView);
                    break;
            }

            VaadinSession.getCurrent().getLockInstance().unlock();
        });

        UI.setCurrent(ui);

        UI.getCurrent().access(() -> {
            VaadinSession.getCurrent().getLockInstance().lock();
            UI.getCurrent().getSession().setAttribute("viewContainer", this);
            VaadinSession.getCurrent().getLockInstance().unlock();
        });

        CurrentPageDimensions.update();
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
