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
import com.vaadin.flow.component.checkbox.CheckboxGroupVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.radiobutton.RadioGroupVariant;
import com.vaadin.flow.component.textfield.TextField;

public class HostLoginView extends AbsoluteLayout{
    private ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));

    private Button send;
    private VerticalLayout options;
    private RadioButtonGroup<String> difficulties;
    private CheckboxGroup<String> types;
    private TextField numQuestions;

    public HostLoginView() {
        CurrentPageDimensions.update();
    }

    public void createPage(){
        difficulties = new RadioButtonGroup<>();
        difficulties.isRequired();
        difficulties.setLabel("Difficulty");
        difficulties.addThemeVariants(RadioGroupVariant.LUMO_VERTICAL);
        difficulties.setItems(AllQuestions.diffs);

        types = new CheckboxGroup<>();
        types.isRequired();
        types.setLabel("Question Types (Leave blank for all)");
        types.addThemeVariants(CheckboxGroupVariant.LUMO_VERTICAL);
        types.setItems(AllQuestions.types);

        numQuestions = new TextField("Number of Questions (Leave blank for 10, max of ___)");

        send = new Button("Create Game", event -> {
            if(difficulties.isEmpty()){
                Notification.show("Please select difficulty");
            }else if(types.isEmpty() || numQuestions.isEmpty()){
                if(types.isEmpty() && !numQuestions.isEmpty()){
                    createGame(difficulties.getValue(), AllQuestions.types, numQuestions.getValue());
                }else if(!types.isEmpty() && numQuestions.isEmpty()){
                    createGame(difficulties.getValue(), types.getValue().toArray(new String[0]), "10");
                }else {
                    createGame(difficulties.getValue(), AllQuestions.types, "10");
                }
            }else{
                createGame(difficulties.getValue(), types.getValue().toArray(new String[0]), numQuestions.getValue());
            }
        });

        options = new VerticalLayout(difficulties, types, numQuestions);

        this.add(options, CurrentPageDimensions.getHeight()/3, CurrentPageDimensions.getWidth() /5);
        this.add(send, CurrentPageDimensions.getHeight() * 3/4, CurrentPageDimensions.getWidth() /3);
    }

    public void createGame(String difficulty, String[] questionTypes, String numQuestions){
        String gameNumber = "";
        for(int i = 0; i < 4; i++) gameNumber += (int)(Math.random() * 10);
        UI.getCurrent().getSession().setAttribute("gameNumber", gameNumber);

        AllGames.addGame(new Game(
                gameNumber,
                new Identifier(difficulty, questionTypes),
                Integer.parseInt(numQuestions)
        ));

        ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer")).changeToView("hostStartGameView");
    }
}
