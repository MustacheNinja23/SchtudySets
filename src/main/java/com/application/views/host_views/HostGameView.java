package com.application.views.host_views;

import com.application.views.ViewContainer;
import com.application.views.backend.broadcasters.JoinGameEventBroadcaster;
import com.application.views.backend.broadcasters.StartGameEventBroadcaster;
import com.application.views.backend.broadcasters.UpdateScoreEventBroadcaster;
import com.application.views.backend.game_classes.AllGames;
import com.application.views.backend.game_classes.Game;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.shared.Registration;

/*
    View the host will wait on while other active sessions
    connect to the same Game

    Displays the gameNumber associated with the Game

    Contains a Button to start the Game, changing the host's active View to
    HostLeaderboardView and activating a StartGameEventBroadcaster to emit an event to
*/
public class HostGameView extends AbsoluteLayout {
    // internal
    private final ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    private Registration updateScoreRegistration, joinGameRegistration;
    private String gameNumber = "";
    private Game game;

    public HostGameView() {
        CurrentPageDimensions.update();
    }

    // Event Listener for ScoreUpdateEvents sent by users in the same game
    @Override
    protected void onAttach(AttachEvent attachEvent) {
        updateScoreRegistration = UpdateScoreEventBroadcaster.register(scoreUpdate -> {
            if (scoreUpdate.getUser().getGameNumber().equals(gameNumber)) {
                scoreUpdate.updateScore(1);
                container.changeToView("hostGameView");
            }
        });

        joinGameRegistration = JoinGameEventBroadcaster.register(userJoined -> {
            if (AllGames.allGames.get(gameNumber).getUsersAsList().contains(userJoined)) {
                System.out.println("user joined game " + gameNumber);
                container.changeToView("hostGameView");
            }
        });
    }

    // Remove Event Listener on detach
    @Override
    protected void onDetach(DetachEvent detachEvent) {
        updateScoreRegistration.remove();
        updateScoreRegistration = null;
        joinGameRegistration.remove();
        joinGameRegistration = null;
    }

    public void createPage() {
        //game number display
        gameNumber = container.getGameNumber();
        for (int i = 0; i < 4; i++) {
            Div numDiv = new Div();
            H1 numText = new H1("" + gameNumber.charAt(i));
            numDiv.setWidth((float) CurrentPageDimensions.getWidth() / 15, Unit.PIXELS);
            numDiv.setHeight((float) CurrentPageDimensions.getHeight() / 10, Unit.PIXELS);
            numText.getStyle().set("font-size", "50");
            numDiv.getStyle().set("background-color", "gray");
            numDiv.getStyle().set("text-align", "center");

            numDiv.add(numText);

            this.add(numDiv, CurrentPageDimensions.getHeight() / 12, CurrentPageDimensions.getWidth() / 3 + (CurrentPageDimensions.getWidth() * i / 12));
        }

        game = AllGames.allGames.get(gameNumber);

        //start game button
        // elements
        Button startGame = new Button("Start Game", _ -> {
            StartGameEventBroadcaster.broadcast(gameNumber);
            game.startGame();
        });
        startGame.getStyle().set("font-size", "30");

        if (!game.isStarted()) {
            this.add(startGame, CurrentPageDimensions.getHeight() * 13 / 16, CurrentPageDimensions.getWidth() / 4);
        }

        //end game button
        Button endGame = new Button("End Game", _ -> AllGames.allGames.remove(gameNumber));
        startGame.getStyle().set("font-size", "30");

        add(endGame, CurrentPageDimensions.getHeight() * 13 / 16, CurrentPageDimensions.getWidth() * 10 / 16);

        Leaderboard leaderboard = new Leaderboard(gameNumber);
        leaderboard.createPage();
        add(leaderboard, CurrentPageDimensions.getHeight() / 4, CurrentPageDimensions.getWidth() / 8);
    }
}
