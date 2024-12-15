package com.application.views.UserLoginView;

import com.application.views.MainLayout;
import com.application.views.backend.AbsoluteLayout;
import com.application.views.backend.CurrentPageDimensions;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@PageTitle("User Login")
@Route(value = "", layout = MainLayout.class)
public class UserLoginView extends AbsoluteLayout implements AfterNavigationObserver {

    float pageHeight;

    TextField gameNum, nickName;
    Button send;
    VerticalLayout items;

    public UserLoginView() {
        items = new VerticalLayout();
        add(items);
        items.setVisible(false);
    }

    public void createPage() {
        gameNum = new TextField();
        nickName = new TextField();
        send = new Button("Send");

        gameNum.setPlaceholder("Game Number");
        nickName.setPlaceholder("Nickname");
        gameNum.setRequired(true);

        send.addClickListener(e -> {
            System.out.println(gameNum.getValue());
            System.out.println(nickName.getValue());
            if(!nickName.getValue().isEmpty() && !gameNum.getValue().isEmpty()) {
                UI.getCurrent().navigate("question");
            }else{
                Notification.show("Please enter a Game Number and a Nickname");
            }
        });
        send.addClickShortcut(Key.ENTER);

        Button goToHostView = new Button("host game", e -> {
            UI.getCurrent().navigate("host");
        });

        VerticalLayout items = new VerticalLayout(gameNum, nickName, send, goToHostView);
        items.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        items.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        items.setHeight((float) CurrentPageDimensions.getHeight() /3, Unit.PIXELS);
        items.setWidth((float) CurrentPageDimensions.getWidth() /3, Unit.PIXELS);

        this.add(items, CurrentPageDimensions.getHeight()/3, CurrentPageDimensions.getWidth()/3);
        this.setSizeUndefined();

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            remove(items);
            System.out.println("It Works!");
            this.add(items, e.getHeight()/3, e.getWidth()/3);
        });
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        createPage();
    }
}
