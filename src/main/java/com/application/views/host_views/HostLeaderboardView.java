package com.application.views.host_views;

import com.application.views.ViewContainer;
import com.application.views.backend.AbsoluteLayout;
import com.application.views.backend.CurrentPageDimensions;
import com.application.views.backend.broadcasters.StartGameEventBroadcaster;
import com.application.views.backend.broadcasters.UpdateScoreBroadcaster;
import com.application.views.backend.game_classes.AllGames;
import com.application.views.backend.game_classes.Game;
import com.application.views.backend.game_classes.User;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.shared.Registration;

public class HostLeaderboardView extends AbsoluteLayout {
    private ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));

    private Registration registration;

    Game game;

    private String gameNumber = "";

    private Div leaderboard;
    private Button endGame;

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        registration = UpdateScoreBroadcaster.register(scoreUpdate -> {
            if(scoreUpdate.getUser().getGameNumber().equals(gameNumber)) {
                scoreUpdate.getUser().updateScore(1);
                createPage();
            }
        });
    }

    @Override
    protected void onDetach(DetachEvent detachEvent){
        registration.remove();
        registration = null;
    }

    public HostLeaderboardView(){
        CurrentPageDimensions.update();
    }

    public void initialize(){
        gameNumber = container.getGameNumber();
        game = AllGames.getGame(gameNumber);
    }

    public void createPage(){
        initialize();

        leaderboard = new Div();
        leaderboard.setHeight((float) (CurrentPageDimensions.getHeight() * 2) /3, Unit.PIXELS);
        leaderboard.setWidth((float) (CurrentPageDimensions.getWidth() * 4) /5, Unit.PIXELS);
        leaderboard.getStyle().set("background-color", "gray");

        Div temp;
        for(User u : game.getUsersAsList()){
            temp = new Div(u.getNickName() + " : " + u.getScore());
            leaderboard.add(temp);
        }

        add(leaderboard, CurrentPageDimensions.getHeight()/6, CurrentPageDimensions.getWidth()/10);

        endGame = new Button("End Game");
        endGame.setHeight((float) (CurrentPageDimensions.getHeight())/10, Unit.PIXELS);
        endGame.setWidth((float) (CurrentPageDimensions.getWidth())/10, Unit.PIXELS);
        endGame.getStyle().set("background-color", "blue");

        add(endGame, CurrentPageDimensions.getHeight() * 9/10, CurrentPageDimensions.getWidth() * 2/5);
    }


}
