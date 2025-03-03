package com.application.views.user_views;

import com.application.views.backend.broadcasters.ScoreUpdateEvent;
import com.application.views.backend.broadcasters.UpdateScoreEventBroadcaster;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.question_classes.Answer;
import com.application.views.backend.question_classes.Question;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.StreamResource;

import java.util.ArrayList;

/*
    Contains the visual representation of a Question instance
    that can be interacted with by an active user

    Contained within the QuestionContainer view
*/
public class QuestionView extends AbsoluteLayout {
    // internal
    private final Question question;
    private final User me;
    private boolean questionCompleted = false;
    // elements
    private TextField answerField;
    private H2 questionDisplay;
    private VectorFieldView vectorArea;

    private int width, height;

    public QuestionView(User me, Question question) {
        CurrentPageDimensions.update();
        this.question = question;
        this.me = me;
    }

    public void setSize() {
        width = CurrentPageDimensions.getComponentWidth(this);
        height = CurrentPageDimensions.getComponentHeight(this);
    }

    public void createPage() {
        this.removeAll();

        switch (question.getQuestionType()) {
            case "open":
                questionDisplay = new H2(question.getQues());
                questionDisplay.setWidth((float) (width * 5) / 12, Unit.PIXELS);

                answerField = new TextField("Answer");

                Button submit = new Button("Submit", _ -> {
                    Answer ans = new Answer(question.getQuestionType(), new Double[]{Double.parseDouble(answerField.getValue())});
                    if (ans.compareTo(question.getAnswer()) == 0) {
                        UpdateScoreEventBroadcaster.broadcast(new ScoreUpdateEvent(me));
                        questionCompleted = true;
                        createPage();
                    } else {
                        Notification.show("Try Again");
                    }
                });

                Image image = new Image(new StreamResource("image", () -> getClass().getResourceAsStream(question.getImageAddress())), "");
                image.setHeight("100px");
                image.setWidth("100px");

                if (!questionCompleted) {
                    this.add(new Div(answerField, submit), height * 5 / 6, width / 12);
                } else {
                    Div complete = new Div();
                    complete.setHeight((float) height / 6, Unit.PIXELS);
                    complete.setWidth((float) width / 3, Unit.PIXELS);

                    Image checkmark = new Image(new StreamResource("checkmark.png", () -> getClass().getResourceAsStream("/checkmark.png")), "");
                    checkmark.setWidth((float) width / 24, Unit.PIXELS);
                    checkmark.setHeight((float) width / 24, Unit.PIXELS);

                    complete.add(checkmark, new Text("The answer was " + question.getAnswer().toString()));

                    this.add(complete, height * 3 / 4, width / 12);
                }

                this.add(questionDisplay, height / 24, width / 24);

                if (image.getSrc() != null && !image.getSrc().isEmpty())
                    this.add(image, height / 3, width / 12);

                vectorArea = new VectorFieldView(width / 2, height);
                vectorArea.getStyle().set("border", "1px solid black");
                vectorArea.createPage();
                this.add(vectorArea, 0, width / 2);

                break;

            case "vector":
                questionDisplay = new H2(question.getQues());
                questionDisplay.setWidth((float) (width * 11) / 12, Unit.PIXELS);

                submit = new Button("Submit", _ -> {
                    ArrayList<Double> arr = new ArrayList<>();
                    for (VectorCanvas.VectorAreaShape d : vectorArea.getCanvas().getShapes())
                        arr.add(d.getVecAngle());
                    Answer ans = new Answer(question.getQuestionType(), arr.toArray(Double[]::new));
                    if (ans.compareTo(question.getAnswer()) == 0) {
                        UpdateScoreEventBroadcaster.broadcast(new ScoreUpdateEvent(me));
                        questionCompleted = true;
                        createPage();
                    } else {
                        Notification.show("Try Again");
                    }
                });

                this.add(questionDisplay, height / 24, width / 24);
                if (!questionCompleted) {
                    this.add(submit, height / 6, width * 7 / 10);
                } else {
                    Image checkmark = new Image(new StreamResource("checkmark.png", () -> getClass().getResourceAsStream("/checkmark.png")), "");
                    checkmark.setWidth((float) width / 24, Unit.PIXELS);
                    checkmark.setHeight((float) width / 24, Unit.PIXELS);

                    this.add(checkmark, height / 6, width * 7 / 10);
                }

                vectorArea = new VectorFieldView(width, height * 5 / 6);
                vectorArea.getStyle().set("border", "1px solid black");
                vectorArea.createPage();
                this.add(vectorArea, height / 6, 0);

                break;
        }
    }
}
