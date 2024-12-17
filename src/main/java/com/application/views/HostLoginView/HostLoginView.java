package com.application.views.HostLoginView;

import com.application.views.MainLayout;
import com.application.views.ViewContainer.ViewContainer;
import com.application.views.backend.AbsoluteLayout;
import com.application.views.backend.AllGames;
import com.application.views.backend.CurrentPageDimensions;
import com.application.views.backend.Game;
import com.application.views.backend.questionClasses.AllQuestions;
import com.application.views.backend.questionClasses.Identifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;

public class HostLoginView extends AbsoluteLayout{
    ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));

    Button send;
    VerticalLayout options;
    RadioButtonGroup<String> difficulties;
    CheckboxGroup<String> types;
    TextField numQuestions;

    public HostLoginView() {
        CurrentPageDimensions.update();
        createPage();
    }

    public void createPage(){
        difficulties = new RadioButtonGroup<>();
        difficulties.isRequired();
        difficulties.setLabel("Difficulty");
        difficulties.setItems(AllQuestions.diffs);

        types = new CheckboxGroup<>();
        types.isRequired();
        types.setLabel("Question Types");
        types.setItems(AllQuestions.types);

        numQuestions = new TextField("Number of Questions");

        send = new Button("Create Game", event -> {
            String gameNumber = "";
            for(int i = 0; i < 4; i++) gameNumber += (int)(Math.random() * 10);
            UI.getCurrent().getSession().setAttribute("gameNumber", gameNumber);

            AllGames.allGames.put(gameNumber, new Game(
                    gameNumber,
                    new Identifier(difficulties.getValue(), types.getValue().toArray(new String[0])),
                    Integer.parseInt(numQuestions.getValue())
            ));

            container.changeToView("hostStartGamerView");
        });

        options = new VerticalLayout(difficulties, types, numQuestions);

        this.add(options, CurrentPageDimensions.getHeight()/3, CurrentPageDimensions.getWidth()/5);
        this.add(send, CurrentPageDimensions.getHeight() * 3/4, CurrentPageDimensions.getWidth() /3);

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            this.removeAll();
            CurrentPageDimensions.update(e);
            createPage();
        });
    }
}
