package com.application.views.QuestionView;

import com.application.views.MainLayout;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.io.FileNotFoundException;

@PageTitle("Questions")
@Route(value = "question", layout = MainLayout.class)
public class QuestionView extends VerticalLayout {

    public QuestionView() throws FileNotFoundException {
        Button button = new Button("Enter");

        setMargin(true);
        setPadding(true);
        setHorizontalComponentAlignment(Alignment.CENTER, button);

        add(button);
    }

}
