package com.application.views.backend;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.DomEvent;
import com.vaadin.flow.component.button.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class StartGameEventBroadcaster{
    private static final List<Consumer<String>> listeners = new CopyOnWriteArrayList<>();
    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    // Register a listener (e.g., a UI session)
    public static Registration register(Consumer<String> listener) {
        listeners.add(listener);
        return () -> listeners.remove(listener);
    }

    // Broadcast a message to all registered listeners
    public static void broadcast(String message) {
        for (Consumer<String> listener : listeners) {
            executor.execute(() -> listener.accept(message));
        }
    }

    // Functional interface for unregistration
    public interface Registration {
        void remove();
    }

}
