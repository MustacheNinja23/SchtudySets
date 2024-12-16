package com.application.views.HostLoginView;

import com.application.views.MainLayout;
import com.application.views.backend.AbsoluteLayout;
import com.application.views.backend.AllGames;
import com.application.views.backend.CurrentPageDimensions;
import com.application.views.backend.Game;
import com.application.views.backend.questionClasses.AllQuestions;
import com.application.views.backend.questionClasses.Identifier;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;

@PageTitle("Host Login")
@Route(value = "host", layout = MainLayout.class)
public class HostLoginView extends AbsoluteLayout implements AfterNavigationObserver {
    static int rel = 0;

    Button send;

    Div options;

    RadioButtonGroup<String> difficulties;
    CheckboxGroup<String> types;
    TextField numQuestions;

    public HostLoginView() {
        CurrentPageDimensions.update();
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
            VaadinSession.getCurrent().getSession().setAttribute("gameNumber", gameNumber);

            AllGames.allGames.put(gameNumber, new Game(
                    gameNumber,
                    new Identifier(difficulties.getValue(), types.getValue().toArray(new String[0])),
                    Integer.parseInt(numQuestions.getValue())
            ));

            UI.getCurrent().navigate("hostStartGame");
        });

        options = new Div(difficulties, types, numQuestions);

        this.add(options, CurrentPageDimensions.getHeight()/3, CurrentPageDimensions.getWidth()/5);
        this.add(send, CurrentPageDimensions.getHeight() * 3/4, CurrentPageDimensions.getWidth() /3);

        UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
            this.removeAll();
            CurrentPageDimensions.update(e);
            createPage();
        });
    }

    @Override
    public void afterNavigation(AfterNavigationEvent afterNavigationEvent) {
        this.setVisible(rel != 0);
        createPage();
        if(rel == 0) {
            UI.getCurrent().getPage().reload();
            rel++;
            this.setVisible(true);
        }
    }
}
