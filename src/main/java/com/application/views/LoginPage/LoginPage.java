package com.application.views.LoginPage;

import com.application.views.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("LoginPage")
@Route(value = "", layout = MainLayout.class)
public class LoginPage extends VerticalLayout {
    TextField gameNum;
    Button send;
    public LoginPage() {
        gameNum = new TextField();
        send = new Button("Send");

        gameNum.setPlaceholder("Game Number");
        gameNum.setRequired(true);

        send.addClickListener(e -> {
            System.out.println(gameNum.getValue());
        });
        send.addClickShortcut(Key.ENTER);

        setMargin(true);
        setPadding(true);
        setHorizontalComponentAlignment(Alignment.CENTER, gameNum, send);

        add(gameNum, send);
    }

}
