package com.application.views.UserLoginView;

import com.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("User Login")
@Route(value = "", layout = MainLayout.class)
public class UserLoginView extends HorizontalLayout {

    TextField gameNum, nickName;
    Button send;
    VerticalLayout layout;

    public UserLoginView() {
        gameNum = new TextField();
        nickName = new TextField();
        send = new Button("Send");

        gameNum.setPlaceholder("Game Number");
        nickName.setPlaceholder("Nickname");
        gameNum.setRequired(true);

        send.addClickListener(e -> {
            System.out.println(gameNum.getValue());
            System.out.println(nickName.getValue());
            UI.getCurrent().navigate("question");
        });
        send.addClickShortcut(Key.ENTER);

        Button goToHostView = new Button("host game", event -> {
            UI.getCurrent().navigate("host");
        });

        this.setSizeFull();
        this.setVerticalComponentAlignment(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultVerticalComponentAlignment(Alignment.CENTER);

        layout = new VerticalLayout(Alignment.CENTER, gameNum, nickName, send, goToHostView);
        layout.setMargin(true);
        layout.setPadding(true);
        layout.setSizeFull();
        layout.setJustifyContentMode(JustifyContentMode.CENTER);
        layout.setAlignItems(Alignment.CENTER);

        this.add(layout);
    }

}
