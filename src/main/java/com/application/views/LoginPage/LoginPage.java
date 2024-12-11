package com.application.views.LoginPage;

import com.application.views.MainLayout;
import com.application.views.QuestionView.QuestionView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("LoginPage")
@Route(value = "", layout = MainLayout.class)
public class LoginPage extends VerticalLayout {
    TextField gameNum, nickName;
    Button send;
    public LoginPage() {
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

        setMargin(true);
        setPadding(true);
        setHorizontalComponentAlignment(Alignment.CENTER, gameNum, nickName, send);

        add(
                gameNum,
                nickName,
                send
        );
    }

}
