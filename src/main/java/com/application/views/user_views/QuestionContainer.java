package com.application.views.user_views;

import com.application.views.ViewContainer;
import com.application.views.backend.CurrentPageDimensions;
import com.application.views.backend.game_classes.AllGames;
import com.application.views.backend.game_classes.Game;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.question_classes.Question;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;

public class QuestionContainer extends HorizontalLayout {
    private ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    private User me = ((User) container.getUi().getSession().getAttribute("currentUser"));

    private Game game;
    private ArrayList<Question> questions;
    private ArrayList<QuestionView> questionViews;

    private String gameNumber;

    Div linkArea;
    Div questionArea;

    public QuestionContainer(){
        CurrentPageDimensions.update();
    }

    public void initialize(){
        gameNumber = container.getGameNumber();
        game = AllGames.getGame(gameNumber);
        questions = game.getQuestions();
        questionViews = new ArrayList<>();
    }

    public void createPage(){
        initialize();
        System.out.println("asd");

        for(Question question : questions){
            questionViews.add(new QuestionView(me, question));
        }

        questionArea = questionViews.get(0);

        linkArea = new Div();
        linkArea.getStyle().set("background-color", "gray");
        linkArea.setHeight(CurrentPageDimensions.getHeight(), Unit.PIXELS);
        linkArea.setWidth((float) CurrentPageDimensions.getWidth() /6, Unit.PIXELS);
        VerticalLayout layout = new VerticalLayout();
        linkArea.add(layout);
        for(int i = 0; i < questions.size(); i++){
            int finalI = i;
            layout.add(new Button("Question " + (i+1), event -> {
                questionArea.removeAll();
                questionArea = questionViews.get(finalI);
            }));
        }

        add(linkArea, questionArea);
    }

}
