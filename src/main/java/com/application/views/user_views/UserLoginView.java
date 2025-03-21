package com.application.views.user_views;

import com.application.views.ViewContainer;
import com.application.views.backend.broadcasters.JoinGameEventBroadcaster;
import com.application.views.backend.game_classes.AllGames;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

/*
    Initial active View for all sessions
    Contains the logic to register as a User in an active Game instance
    Links to the HostLoginView
*/
public class UserLoginView extends AbsoluteLayout {
    // internal
    private final ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));

    // elements
    private TextField gameNum, nickName;

    public UserLoginView() {
        CurrentPageDimensions.update();
        createPage();
    }

    public void createPage() {
        gameNum = new TextField();
        nickName = new TextField();
        Button send = new Button("Send");

        gameNum.setPlaceholder("Game Number");
        nickName.setPlaceholder("Nickname");

        send.addClickListener(_ -> {
            if (!nickName.getValue().isEmpty() && !gameNum.getValue().isEmpty()) {
                if (AllGames.allGames.containsKey(gameNum.getValue())) {
                    boolean checkName = true;
                    for (User u : AllGames.allGames.get(gameNum.getValue()).getUsersAsList()) {
                        if (u.getNickName().equals(nickName.getValue())) {
                            checkName = false;
                            break;
                        }
                    }

                    if (checkName) {
                        User me = new User(nickName.getValue(), gameNum.getValue());
                        AllGames.allGames.get(gameNum.getValue()).addUser(me);
                        container.setGameNumber(gameNum.getValue());
                        container.initializeQuestionContainer(nickName.getValue());
                        JoinGameEventBroadcaster.broadcast(me);
                        container.changeToView("waitingView");
                    } else {
                        Notification.show("Nickname is already in use");
                    }
                } else {
                    Notification.show("Game Number does not exist");
                }
            } else {
                Notification.show("Please enter a Game Number and a Nickname");
            }
        });

        Button goToHostView = new Button("host game", _ -> container.changeToView("hostLoginView"));

        VerticalLayout items = new VerticalLayout(gameNum, nickName, send, goToHostView);
        items.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        items.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.CENTER);
        items.setHeight((float) CurrentPageDimensions.getHeight() / 3, Unit.PIXELS);
        items.setWidth((float) CurrentPageDimensions.getWidth() / 3, Unit.PIXELS);

        H1 title = new H1("Schutudy Sets");
        title.setWidth((float) CurrentPageDimensions.getWidth() / 4, Unit.PIXELS);
        title.getStyle().set("font-size", "55px");
        title.getStyle().set("text-align", "center");

        this.add(title, CurrentPageDimensions.getHeight() / 6, CurrentPageDimensions.getWidth() * 3 / 8);
        this.add(items, CurrentPageDimensions.getHeight() / 3, CurrentPageDimensions.getWidth() / 3);
        this.setSizeUndefined();
    }
}
