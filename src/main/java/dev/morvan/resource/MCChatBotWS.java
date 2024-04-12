package dev.morvan.resource;


import dev.morvan.service.MCChatBot;
import io.quarkiverse.langchain4j.ChatMemoryRemover;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.eclipse.microprofile.context.ManagedExecutor;

@ServerEndpoint("/mc-chat")
@ApplicationScoped
public class MCChatBotWS {

    @Inject
    MCChatBot bot;

    @Inject
    ManagedExecutor managedExecutor;

    @OnOpen
    public void onOpen(Session session) {
        managedExecutor.execute(() -> {
            String response = bot.chat(session, "hello");
            try {
                session.getBasicRemote().sendText(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @OnClose
    public void onClose(Session session) {
         ChatMemoryRemover.remove(bot, session);
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        managedExecutor.execute(() -> {
            String response = bot.chat(session, message);
            try {
                session.getBasicRemote().sendText(response);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
