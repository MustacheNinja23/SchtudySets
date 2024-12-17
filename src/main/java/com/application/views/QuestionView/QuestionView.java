package com.application.views.QuestionView;

import com.application.views.ViewContainer.ViewContainer;
import com.application.views.backend.CurrentPageDimensions;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;

public class QuestionView extends AppLayout {
    private ViewContainer container = ((ViewContainer) UI.getCurrent().getSession().getAttribute("viewContainer"));

    public QuestionView(){
        CurrentPageDimensions.update();
    }

    public void createPage(){

    }
}
