package com.application.views.host_views;

import com.application.views.ViewContainer;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.application.views.backend.game_classes.AllGames;
import com.application.views.backend.game_classes.Game;
import com.application.views.backend.game_classes.User;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.shared.Registration;

/*
    View shown on the user session while the game is ongoing
    Displays a leaderboard ranking each User instance by their current points
*/
public class Leaderboard extends VerticalLayout {
    // internal
    private final ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    private Registration registration;
    private Game game;

    // elements
    VerticalLayout leaderboard;

    // Create Page
    public Leaderboard(String gameNumber) {
        CurrentPageDimensions.update();
        game = AllGames.getGame(gameNumber);
    }

    public void createPage(){
        //Credit to https://vaadin.com/forum/t/scrollable-layout/157614 for the scrollable layout
        this.getStyle().set("overflow", "auto");
        this.getStyle().set("border", "1px solid black");
        this.setWidth((float) CurrentPageDimensions.getWidth() * 3/4, Unit.PIXELS);
        this.setHeight((float) CurrentPageDimensions.getHeight() / 2, Unit.PIXELS);
        this.getStyle().set("padding", "0px");
        this.getStyle().set("margin", "0px");
        this.setSpacing(false);

        AbsoluteLayout temp;
        H1 nickName, score;
        game.addUser(new User("me", game.getGameNumber()));
        for(User u : game.getUsersAsSortedList()){
            temp = new AbsoluteLayout();
            temp.setHeight((float) CurrentPageDimensions.getHeight() / 16, Unit.PIXELS);
            temp.setWidth(this.getWidth());
            temp.getStyle().set("border", "1px solid black");
            temp.getStyle().set("background-color", "lightgray");

            nickName = new H1(u.getNickName());
            nickName.getStyle().set("font-size", "40px");
            score = new H1("" + u.getScore());
            score.getStyle().set("font-size", "40px");

            //converts pixel size measurements of the temp element to integers
            //  this requires removing all characters from the decimal point on
            StringBuilder tempWidthString = new StringBuilder();
            for(char c : temp.getWidth().toCharArray()) {
                if(c == 46) break;
                tempWidthString.append(c);
            }
            int tempWidth = Integer.parseInt(tempWidthString.toString());

            StringBuilder tempHeightString = new StringBuilder();
            for(char c : temp.getHeight().toCharArray()) {
                if(c == 46) break;
                tempHeightString.append(c);
            }
            int tempHeight = Integer.parseInt(tempHeightString.toString());

            temp.add(nickName,
                    tempHeight / 8,
                    tempWidth / 16);


            temp.add(score,
                    tempHeight / 8,
                    tempWidth * 13/16);

            this.add(temp);
        }
    }
}
