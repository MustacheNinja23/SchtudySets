package com.application;

import com.application.views.backend.AllQuestions;
import com.application.views.backend.CurrentPageDimensions;
import com.vaadin.flow.component.page.AppShellConfigurator;
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
@Theme(value = "test")
public class Application implements AppShellConfigurator {

    public static void main(String[] args) throws FileNotFoundException {
        AllQuestions.instantiate();

        SpringApplication.run(Application.class, args);
    }
}
