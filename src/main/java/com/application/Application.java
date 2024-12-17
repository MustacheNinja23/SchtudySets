package com.application;

import com.application.views.backend.AllGames;
import com.application.views.backend.questionClasses.AllQuestions;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.shared.communication.PushMode;
import com.vaadin.flow.theme.Theme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

/**
 * The entry point of the Spring Boot application.
 *
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 *
 */
@SpringBootApplication
@Push(PushMode.AUTOMATIC)
@Theme(value = "test")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) throws FileNotFoundException {
        AllQuestions.instantiate();
        AllGames.instantiate();

        SpringApplication.run(Application.class, args);
    }
}
