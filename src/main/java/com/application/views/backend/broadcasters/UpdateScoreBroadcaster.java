package com.application.views.backend.broadcasters;

import com.vaadin.flow.shared.Registration;
import org.atmosphere.cpr.Broadcaster;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class UpdateScoreBroadcaster {
    static Executor executor = Executors.newSingleThreadExecutor();

    static LinkedList<Consumer<ScoreUpdateEvent>> listeners = new LinkedList<>();

    public static synchronized Registration register(
            Consumer<ScoreUpdateEvent> listener) {
        listeners.add(listener);

        return () -> {
            synchronized (Broadcaster.class) {
                listeners.remove(listener);
            }
        };
    }

    public static synchronized void broadcast(ScoreUpdateEvent scoreUpdateEvent) {
        for (Consumer<ScoreUpdateEvent> listener : listeners) {
            executor.execute(() -> listener.accept(scoreUpdateEvent));
        }
    }
}
