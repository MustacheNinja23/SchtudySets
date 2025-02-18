package com.application.views.user_views;

import com.application.views.ViewContainer;
import com.application.views.backend.utils.CurrentPageDimensions;
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

/*
    Contains multiple instances of the QuestionView class, and allows
    changing between these instances through a custom menu on the left
    side of the screen
*/
public class QuestionContainer extends HorizontalLayout {
    // internal
    private ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    private User me;
    private String gameNumber;
    private Game game;

    //elements
    private ArrayList<Question> questions;
    private ArrayList<QuestionView> questionViews;
    private VerticalLayout layout;
    private Div linkArea;
    private Div questionArea;

    public QuestionContainer(String nickName){
        CurrentPageDimensions.update();
        initialize(nickName);
    }

    public void initialize(String nickName){
        gameNumber = container.getGameNumber();
        game = AllGames.getGame(gameNumber);
        me = game.getUser(nickName);
        questions = game.getQuestions();
        questionViews = new ArrayList<>();
    }

    public void createPage(){
        initialize(me.getNickName());

        int questionNumber = 1;
        for(Question question : questions){
            questionViews.add(new QuestionView(me, question, questionNumber));
            questionNumber++;
        }

        questionArea = new Div();
        questionArea.add(questionViews.getFirst());

        linkArea = new Div();
        linkArea.getStyle().set("background-color", "gray");
        linkArea.setHeight(CurrentPageDimensions.getHeight(), Unit.PIXELS);
        linkArea.setWidth((float) CurrentPageDimensions.getWidth() /6, Unit.PIXELS);
        layout = new VerticalLayout();
        linkArea.add(layout);
        for(int i = 0; i < questions.size(); i++){
            int finalI = i;
            layout.add(new Button("Question " + (i+1), event -> {
                questionArea.removeAll();
                questionArea.add(questionViews.get(finalI));
            }));
        }

        add(linkArea, questionArea);
    }

}
