package com.application.views.HostStartGameView;

import com.application.views.MainLayout;
import com.application.views.backend.*;
import com.application.views.backend.questionClasses.Identifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import jakarta.persistence.Id;

@Route(value = "hostStartGame", layout = MainLayout.class)
public class HostStartGameView extends AbsoluteLayout implements AfterNavigationObserver {
    static int rel = 0;

    public String gameNumber = "";

    Game game;

    Button startGame;
    Div nums;
    VirtualList<User> users;

    public HostStartGameView() {
        CurrentPageDimensions.update();
        gameNumber = (String) VaadinSession.getCurrent().getSession().getAttribute("gameNumber");
        game = AllGames.getGame(gameNumber);
    }

    public void createPage(){
        startGame = new Button("Start Game", event -> {
            for(User u : game.getUsers().values()){
                fireStartGameEvent();
            }
        });
        startGame.getStyle().set("font-size", "30");

        for(int i = 1; i < 5; i++){
            nums = new Div("" + gameNumber.charAt(i));
            nums.setWidth((float) CurrentPageDimensions.getWidth() /10, Unit.PIXELS);
            nums.setHeight((float) CurrentPageDimensions.getHeight() /7, Unit.PIXELS);
            nums.getStyle().set("font-size", "30");
            nums.getStyle().set("background-color", "gray");
            nums.getStyle().set("text-align", "center");
            nums.getStyle().set("padding", "10px");
            nums.getStyle().set("margin", "10px");
            this.add(nums, CurrentPageDimensions.getHeight() /4, CurrentPageDimensions.getWidth()/4 + (CurrentPageDimensions.getWidth() * i/12));
        }

        this.add();

        this.add(startGame, CurrentPageDimensions.getHeight() * 3/4, CurrentPageDimensions.getWidth() * 5/12);

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            this.removeAll();
            CurrentPageDimensions.update(e);
            createPage();
        });
    }

    public void fireStartGameEvent() {
        fireEvent(new StartGameEvent(this, false));
    }

    public String getGameNumber() {
        return gameNumber;
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
}
