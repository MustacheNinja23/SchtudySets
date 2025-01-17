package com.application.views.user_views;

import com.application.views.ViewContainer;
import com.application.views.backend.broadcasters.StartGameEventBroadcaster;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.shared.Registration;

/*
    The View seen by an active user while waiting for the game they
    joined to be started by the host
*/
public class WaitingView extends AbsoluteLayout { //TODO: waiting
    // internal
    private ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    Registration registration;
    private User me = ((User) container.getUi().getSession().getAttribute("currentUser"));

    //elements
    private H1 title;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        registration = StartGameEventBroadcaster.register(message -> {
            if(message.equals(container.getGameNumber())){
                container.changeToView("questionView");
            }
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent){
        registration.remove();
        registration = null;
    }

    public WaitingView() {
        CurrentPageDimensions.update();
    }

    public void createPage(){
        title = new H1("Waiting for host...");
        title.getStyle().set("font-size", "60px");
        title.setWidth(CurrentPageDimensions.getWidth(), Unit.PIXELS);

        this.add(title, CurrentPageDimensions.getHeight() * 5/12, CurrentPageDimensions.getWidth() * 5/12);





    }
}
