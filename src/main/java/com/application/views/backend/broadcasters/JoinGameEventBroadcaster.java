package com.application.views.backend.broadcasters;

import com.application.views.backend.game_classes.User;
import com.vaadin.flow.shared.Registration;
import org.atmosphere.cpr.Broadcaster;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class JoinGameEventBroadcaster {
    static Executor executor = Executors.newSingleThreadExecutor();

    static LinkedList<Consumer<User>> listeners = new LinkedList<>();

    public static synchronized Registration register(Consumer<User> listener) {
        listeners.add(listener);

        return () -> {
            synchronized (Broadcaster.class) {
                listeners.remove(listener);
            }
        };
    }

    public static synchronized void broadcast(User user) {
        System.out.println("broadcast JoinGameEvent to game " + user.getGameNumber());
        for (Consumer<User> listener : listeners) {
            executor.execute(() -> {
                listener.accept(user);
            });
        }
    }
}
