package com.application.views.backend.broadcasters;

import com.vaadin.flow.shared.Registration;
import org.atmosphere.cpr.Broadcaster;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class UpdateScoreEventBroadcaster {
    static Executor executor = Executors.newSingleThreadExecutor();

    static LinkedList<Consumer<ScoreUpdateEvent>> listeners = new LinkedList<>();

    public static synchronized Registration register(Consumer<ScoreUpdateEvent> listener) {
        listeners.add(listener);

        return () -> {
            synchronized (Broadcaster.class) {
                listeners.remove(listener);
            }
        };
    }

    public static synchronized void broadcast(ScoreUpdateEvent scoreUpdateEvent) {
        System.out.println("broadcast UpdateScoreEvent to game " + scoreUpdateEvent.getUser().getGameNumber());
        for (Consumer<ScoreUpdateEvent> listener : listeners) {
            executor.execute(() -> listener.accept(scoreUpdateEvent));
        }
    }
}
