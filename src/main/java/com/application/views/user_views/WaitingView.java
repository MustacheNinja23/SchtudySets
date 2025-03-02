package com.application.views.user_views;

import com.application.views.ViewContainer;
import com.application.views.backend.broadcasters.StartGameEventBroadcaster;
import com.application.views.backend.game_classes.AllGames;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.shared.Registration;

/*
    The View seen by an active user while waiting for the game they
    joined to be started by the host
*/
public class WaitingView extends AbsoluteLayout { //TODO: waiting
    // internal
    private final ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    Registration registration;

    public WaitingView() {
        CurrentPageDimensions.update();
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        //check if the game is already started, join immediately if so
        //  otherwise, await the game start broadcast
        if (AllGames.allGames.get(container.getGameNumber()).isStarted()) {
            container.changeToView("questionView");
        }

        registration = StartGameEventBroadcaster.register(message -> {
            if (message.equals(container.getGameNumber())) {
                container.changeToView("questionView");
            }
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        registration.remove();
        registration = null;
    }

    public void createPage() {
        H1 title = new H1("Waiting for host...");
        title.getStyle().set("font-size", "60px");
        title.setWidth(CurrentPageDimensions.getWidth(), Unit.PIXELS);

        this.add(title, CurrentPageDimensions.getHeight() * 5 / 12, CurrentPageDimensions.getWidth() * 5 / 12);
    }
}
