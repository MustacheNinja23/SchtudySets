package com.application.views.user_views;

import com.application.views.ViewContainer;
import com.application.views.backend.game_classes.AllGames;
import com.application.views.backend.game_classes.Game;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.question_classes.Question;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.ArrayList;

/*
    Contains multiple instances of the QuestionView class, and allows
    changing between these instances through a custom menu on the left
    side of the screen
*/
public class QuestionContainer extends AbsoluteLayout {
    // internal
    private final ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    private User me;
    private String gameNumber;
    private Game game;

    //elements
    private ArrayList<Question> questions;
    private ArrayList<QuestionView> questionViews;
    private VerticalLayout layout;
    private Div linkArea;
    private Div questionArea;

    public QuestionContainer(String nickName) {
        CurrentPageDimensions.update();
        initialize(nickName);
    }

    public void initialize(String nickName) {
        gameNumber = container.getGameNumber();
        game = AllGames.getGame(gameNumber);
        me = game.getUser(nickName);
        questions = game.getQuestions();
        questionViews = new ArrayList<>();
    }

    public void createPage() {
        initialize(me.getNickName());

        for (int i = 0; i < questions.size(); i++) {
            questionViews.add(new QuestionView(me, questions.get(i)));
            questionViews.get(i).setWidth(CurrentPageDimensions.getWidth() / 2 + "");
        }

        linkArea = new Div();
        linkArea.getStyle().set("background-color", "gray");
        linkArea.setHeight(CurrentPageDimensions.getHeight(), Unit.PIXELS);
        linkArea.setWidth((float) CurrentPageDimensions.getWidth() / 6, Unit.PIXELS);
        layout = new VerticalLayout();
        layout.getStyle().set("overflow", "auto");
        linkArea.add(layout);
        for (int i = 0; i < questions.size(); i++) {
            int finalI = i;
            layout.add(new Button("Question " + (i + 1), event -> {
                questionArea.removeAll();
                questionViews.get(finalI).createPage();
                questionArea.add(questionViews.get(finalI));
            }));
        }

        for (QuestionView questionView : questionViews) {
            questionView.setHeight(CurrentPageDimensions.getHeight(), Unit.PIXELS);
            questionView.setWidth((float) CurrentPageDimensions.getWidth() * 5 / 6, Unit.PIXELS);
            questionView.setSize();
        }

        questionArea = new Div();
        questionArea.setHeight(CurrentPageDimensions.getHeight(), Unit.PIXELS);
        questionArea.setWidth((float) CurrentPageDimensions.getWidth() * 5 / 6, Unit.PIXELS);

        questionViews.getFirst().createPage();
        questionArea.add(questionViews.getFirst());

        this.add(linkArea, 0, 0);
        this.add(questionArea, 0, CurrentPageDimensions.getWidth() / 6);
    }
}
