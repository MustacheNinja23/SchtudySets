package com.application.views.UserLoginView;

import com.application.views.MainLayout;
import com.application.views.ViewContainer.ViewContainer;
import com.application.views.backend.AbsoluteLayout;
import com.application.views.backend.AllGames;
import com.application.views.backend.CurrentPageDimensions;
import com.application.views.backend.User;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinSession;

public class UserLoginView extends AbsoluteLayout{
    ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));

    H1 title;
    TextField gameNum, nickName;
    Button send;
    VerticalLayout items;

    public UserLoginView() {
        CurrentPageDimensions.update();
        createPage();
    }

    public void createPage() {
        gameNum = new TextField();
        nickName = new TextField();
        send = new Button("Send");

        gameNum.setPlaceholder("Game Number");
        nickName.setPlaceholder("Nickname");
        gameNum.setRequired(true);

        send.addClickListener(e -> {
            if(!nickName.getValue().isEmpty() && !gameNum.getValue().isEmpty()) {
                for(String s : AllGames.allGames.keySet()){
                    if(gameNum.getValue().equals(s)){
                        User me = new User(VaadinSession.getCurrent(), nickName.getValue(), gameNum.getValue());
                        AllGames.allGames.get(gameNum.getValue()).addUser(me);
                        UI.getCurrent().getSession().setAttribute("currentUser", me);
                    }
                }
            }else{
                Notification.show("Please enter a Game Number and a Nickname");
            }
        });
        send.addClickShortcut(Key.ENTER);

        Button goToHostView = new Button("host game", e -> {
            container.changeToView("hostLoginView");
        });

        VerticalLayout items = new VerticalLayout(gameNum, nickName, send, goToHostView);
        items.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        items.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        items.setHeight((float) CurrentPageDimensions.getHeight() /3, Unit.PIXELS);
        items.setWidth((float) CurrentPageDimensions.getWidth() /3, Unit.PIXELS);

        title = new H1("Schutudy Sets");
        title.setWidth((float) CurrentPageDimensions.getWidth() /4, Unit.PIXELS);
        title.getStyle().set("font-size", "55px");
        title.getStyle().set("text-align", "center");

        this.add(title, CurrentPageDimensions.getHeight()/6, CurrentPageDimensions.getWidth() * 3/8);
        this.add(items, CurrentPageDimensions.getHeight()/3, CurrentPageDimensions.getWidth()/3);
        this.setSizeUndefined();

        //Standard resize listener for all AbsoluteLayout pages
        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            this.removeAll();
            CurrentPageDimensions.update(e);
            createPage();
        });
    }
}
