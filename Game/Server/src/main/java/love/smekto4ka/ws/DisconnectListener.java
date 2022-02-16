package love.smekto4ka.ws;

import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;
import org.springframework.web.socket.sockjs.transport.session.WebSocketServerSockJsSession;

public interface DisconnectListener {
    void handle(StandardWebSocketSession session);
}
