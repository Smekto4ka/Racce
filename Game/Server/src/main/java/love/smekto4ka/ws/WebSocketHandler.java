package love.smekto4ka.ws;

import com.badlogic.gdx.utils.Array;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.annotation.PreDestroy;
import java.io.IOException;

@Component
public class WebSocketHandler extends AbstractWebSocketHandler {
    private final Array<WebSocketSession> sessions = new Array<>();
    private ConnectListener connectListener;
    private DisconnectListener disconnectListener;
    private MessagesListener messagesListener;

    @PreDestroy
    public void destroy() throws IOException {
        for (WebSocketSession session : sessions) {
            session.close();
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        synchronized (sessions) {
            sessions.add(session);
        }
        connectListener.handle(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        messagesListener.handle(session, message.getPayload());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        synchronized (sessions){
            sessions.removeValue(session, true);
        }

        disconnectListener.handle(session);
    }

    public Array<WebSocketSession> getSessions() {
        return sessions;
    }

    public void setConnectListener(ConnectListener connectListener) {
        this.connectListener = connectListener;
    }

    public void setDisconnectListener(DisconnectListener disconnectListener) {
        this.disconnectListener = disconnectListener;
    }

    public void setMessagesListener(MessagesListener messagesListener) {
        this.messagesListener = messagesListener;
    }
}
