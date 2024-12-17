package com.application.views.WaitingView;

import com.application.views.HostStartGameView.HostStartGameView;
import com.application.views.ViewContainer.ViewContainer;
import com.application.views.backend.*;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;
import org.atmosphere.cpr.Broadcaster;
import org.springframework.context.event.EventListener;

public class WaitingView extends AbsoluteLayout{
    private ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));

    private User me = VaadinSession.getCurrent().getAttribute(User.class);

    public WaitingView() {
        CurrentPageDimensions.update();
    }

    public void createPage(){
        H1 title = new H1("Waiting for host...");
        title.getStyle().set("font-size", "60px");
        title.setWidth(CurrentPageDimensions.getWidth(), Unit.PIXELS);

        this.add(title, CurrentPageDimensions.getHeight() * 5/12, CurrentPageDimensions.getWidth() * 5/12);





    }

//    @EventListener
//    public void onStartGame(StartGameEvent event){
//        UI.getCurrent().access(() -> {
//            container.changeToView("questionView");
//        });
//    }
}
