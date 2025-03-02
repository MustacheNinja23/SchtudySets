package com.application.views.backend.broadcasters;

import com.vaadin.flow.shared.Registration;
import org.atmosphere.cpr.Broadcaster;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class StartGameEventBroadcaster {  //https://vaadin.com/docs/latest/flow/advanced/server-push
    static Executor executor = Executors.newSingleThreadExecutor();

    static LinkedList<Consumer<String>> listeners = new LinkedList<>();

    public static synchronized Registration register(
            Consumer<String> listener) {
        listeners.add(listener);

        return () -> {
            synchronized (Broadcaster.class) {
                listeners.remove(listener);
            }
        };
    }

    public static synchronized void broadcast(String gameNumber) {
        System.out.println("broadcast StartGameEvent on game " + gameNumber);
        for (Consumer<String> listener : listeners) {
            executor.execute(() -> listener.accept(gameNumber));
        }
    }
}
