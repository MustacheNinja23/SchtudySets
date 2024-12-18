package com.application.views.user_views;

import com.application.views.backend.AbsoluteLayout;
import com.application.views.backend.CurrentPageDimensions;
import com.application.views.backend.broadcasters.ScoreUpdateEvent;
import com.application.views.backend.broadcasters.UpdateScoreBroadcaster;
import com.application.views.backend.game_classes.User;
import com.application.views.backend.question_classes.Answer;
import com.application.views.backend.question_classes.Question;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.textfield.TextField;

public class QuestionView extends AbsoluteLayout {
    TextField answer;
    Button submit;
    Image image;

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
