package com.application.views.WaitingView;

import com.application.views.HostStartGameView.HostStartGameView;
import com.application.views.MainLayout;
import com.application.views.backend.*;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.shared.Registration;

@PageTitle("Waiting")
@Route(value = "waiting", layout = MainLayout.class)
public class WaitingView extends AbsoluteLayout implements AfterNavigationObserver {
    static int rel = 0;

    User me = VaadinSession.getCurrent().getAttribute(User.class);

    H1 title;

    public WaitingView() {
        CurrentPageDimensions.update();
    }

    public void createPage(){
        title = new H1("Waiting for host...");
        title.getStyle().set("font-size", "60px");
        title.setWidth(CurrentPageDimensions.getWidth(), Unit.PIXELS);

        this.add(title, CurrentPageDimensions.getHeight() * 5/12, CurrentPageDimensions.getWidth() * 5/12);

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            this.removeAll();
            CurrentPageDimensions.update(e);
            createPage();
        });

        this.addStartGameListener(event -> {
            HostStartGameView source = (HostStartGameView) event.getSource();
            if(source.getGameNumber().equals(me.getGameNumber())){
                UI.getCurrent().navigate("question");
            }
        });
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        this.setVisible(rel != 0);
        createPage();
        if(rel == 0) {
            UI.getCurrent().getPage().reload();
            rel++;
            this.setVisible(true);
        }
    }

    public Registration addStartGameListener(ComponentEventListener<StartGameEvent> listener) {
        return addListener(StartGameEvent.class, listener);
    }
}
