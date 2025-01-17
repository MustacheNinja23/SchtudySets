package com.application.views.user_views;

import com.application.views.ViewContainer;
import com.application.views.backend.utils.AbsoluteLayout;
import com.application.views.backend.utils.CurrentPageDimensions;
import com.application.views.backend.broadcasters.ScoreUpdateEvent;
import com.application.views.backend.broadcasters.UpdateScoreBroadcaster;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.question_classes.Answer;
import com.application.views.backend.question_classes.Question;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.TextField;

/*
    Contains the visual representation of a Question instance
    that can be interacted with by an active user
*/
public class QuestionView extends AbsoluteLayout { //TODO: all'a this
    // internal
    private final ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));

    // elements
    private TextField answer;
    private Button submit;
    private Image image;

    public QuestionView(User me, Question question) {
        CurrentPageDimensions.update();

        answer = new TextField("Answer");
        submit = new Button("Submit", event -> {
            Answer ans = new Answer(Double.parseDouble(answer.getValue()));
            if(ans.compareTo(question.getAnswer()) == 0){
                UpdateScoreBroadcaster.broadcast(new ScoreUpdateEvent(me, me.getScore()));
            }
        });

        //image = new Image(question.getImageAdd(), "");

        add(new Text(question.getQues()));
        //add(image, CurrentPageDimensions.getHeight()/2, CurrentPageDimensions.getWidth()/3);
        add(new Div(answer, submit), CurrentPageDimensions.getHeight() * 5/6, CurrentPageDimensions.getWidth()/3);
    }
}
