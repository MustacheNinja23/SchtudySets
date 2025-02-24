package com.application.views.user_views;

import com.application.views.ViewContainer;
import com.application.views.backend.broadcasters.ScoreUpdateEvent;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.application.views.backend.broadcasters.UpdateScoreEventBroadcaster;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.question_classes.Answer;
import com.application.views.backend.question_classes.Question;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;

import java.io.Reader;

/*
    Contains the visual representation of a Question instance
    that can be interacted with by an active user

    Contained within the QuestionContainer view
*/
public class QuestionView extends AbsoluteLayout {
    // internal
    private final ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));
    private boolean questionCompleted = false;
    private Question question;
    private User me;

    // elements
    private TextField answerField;
    private Button submit;
    private Image image;
    private H1 questionDisplay;

    public QuestionView(User me, Question question) {
        CurrentPageDimensions.update();
        this.question = question;
        this.me = me;
        this.setHeight(CurrentPageDimensions.getHeight() + "");
        this.setWidth(CurrentPageDimensions.getWidth() / 2 + "");
    }

    public void createPage() {
        this.removeAll();

        questionDisplay = new H1(question.getQues());

        answerField = new TextField("Answer");

        submit = new Button("Submit", event -> {
            Answer ans = new Answer(Double.parseDouble(answerField.getValue()));
            if(ans.compareTo(question.getAnswer()) == 0) {
                UpdateScoreEventBroadcaster.broadcast(new ScoreUpdateEvent(me));
                questionCompleted = true;
                createPage();
            }
        });

        image = new Image(new StreamResource("image", () -> getClass().getResourceAsStream(question.getImageAddress())), "");
        image.setHeight("100px");
        image.setWidth("100px");

        if(!questionCompleted) {
            this.add(new Div(answerField, submit), CurrentPageDimensions.getComponentHeight(this) * 5/6, CurrentPageDimensions.getComponentWidth(this)/3);
        } else {
            Div complete = new Div();
            complete.setHeight((float) CurrentPageDimensions.getComponentHeight(this) / 6, Unit.PIXELS);
            complete.setWidth((float) CurrentPageDimensions.getComponentWidth(this) / 3, Unit.PIXELS);

            Image checkmark = new Image(new StreamResource("checkmark.png", () -> getClass().getResourceAsStream("/checkmark.png")), "");
            checkmark.setWidth((float) CurrentPageDimensions.getComponentWidth(this) / 12, Unit.PIXELS);
            checkmark.setHeight(this.getWidth());

            complete.add(checkmark, new Text("The answer was " + question.getAnswer().toString()));

            add(complete, CurrentPageDimensions.getComponentHeight(this) * 3/4, CurrentPageDimensions.getComponentWidth(this)/3);
        }

        add(questionDisplay);
        if(image.getSrc() != null) add(image, CurrentPageDimensions.getComponentHeight(this)/2, CurrentPageDimensions.getComponentWidth(this)/3);
    }
}
