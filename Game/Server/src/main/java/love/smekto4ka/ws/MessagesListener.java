package love.smekto4ka.ws;

import com.badlogic.gdx.utils.JsonValue;
import org.springframework.web.socket.adapter.standard.StandardWebSocketSession;

public interface MessagesListener {
    void handle(StandardWebSocketSession session, JsonValue message);
}
