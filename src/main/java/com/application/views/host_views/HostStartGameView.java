package com.application.views.host_views;

import com.application.views.ViewContainer;
import com.application.views.backend.broadcasters.StartGameEventBroadcaster;
import com.application.views.backend.game_classes.Game;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.virtuallist.VirtualList;

/*
    View the host will wait on while other active sessions
    connect to the same Game

    Displays the gameNumber associated with the Game

    Contains a Button to start the Game, changing the host's active View to
    HostLeaderboardView and activating a StartGameEventBroadcaster to emit an event to
*/
public class HostStartGameView extends AbsoluteLayout {
    // internal
    private final ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    private String gameNumber = "";
    private Game game;

    // elements
    private VirtualList<User> users;
    Button startGame;

    public HostStartGameView() {
        CurrentPageDimensions.update();
    }

    public void createPage(){
        gameNumber = container.getGameNumber();

        startGame = new Button("Start Game", event -> {
            StartGameEventBroadcaster.broadcast(gameNumber);
            container.changeToView("hostLeaderboardView");
        });
        startGame.getStyle().set("font-size", "30");

        for(int i = 1; i < 5; i++){
            Div nums = new Div("" + gameNumber.charAt(i - 1));
            nums.setWidth((float) CurrentPageDimensions.getWidth() /15, Unit.PIXELS);
            nums.setHeight((float) CurrentPageDimensions.getHeight() /10, Unit.PIXELS);
            nums.getStyle().set("font-size", "30");
            nums.getStyle().set("background-color", "gray");
            nums.getStyle().set("text-align", "center");
            nums.getStyle().set("padding", "10px");
            nums.getStyle().set("margin", "10px");
            this.add(nums, CurrentPageDimensions.getHeight() /4, CurrentPageDimensions.getWidth()/4 + (CurrentPageDimensions.getWidth() * i/12));
        }

        this.add(startGame, CurrentPageDimensions.getHeight() * 3/4, CurrentPageDimensions.getWidth() * 5/12);
    }
}
