package com.application.views.QuestionView;

import com.application.views.MainLayout;
import com.application.views.backend.CurrentPageDimensions;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Questions")
@Route(value = "question", layout = MainLayout.class)
public class QuestionView extends AppLayout implements AfterNavigationObserver {
    static int rel = 0;

    public QuestionView(){
        CurrentPageDimensions.update();
    }

    public void createPage(){

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
