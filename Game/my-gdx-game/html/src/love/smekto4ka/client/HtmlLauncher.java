package love.smekto4ka.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.google.gwt.user.client.Timer;
import love.smekto4ka.Started;
import love.smekto4ka.client.dto.InputStateImpl;
import love.smekto4ka.client.ws.EventListenerCallback;
import love.smekto4ka.client.ws.WebSocket;
import love.smekto4ka.client.ws.WsEvent;

import java.util.concurrent.atomic.AtomicBoolean;

public class HtmlLauncher extends GwtApplication {

    @Override
    public GwtApplicationConfiguration getConfig() {
        // Resizable application, uses available space in browser
        return new GwtApplicationConfiguration(true);
        // Fixed size application:
        //return new GwtApplicationConfiguration(480, 320);
    }

    /*
     * Тут мы создаем сокет (мы ж тут пишем на джаве, а код компилится потом в джава скрипт)
     * А соекты мы брали обычно из спринга, но сами сокеты есть в том же приложении гугл
     *
     *
     * передаем ему юрл для подключения
     * после создается сокет в браузере (код нативный в коменте - JS code)
     * а нам для работы возвращается обертка
     *
     *
     * */
    private native WebSocket getWebSocket(String url)
    /*-{
        return new WebSocket(url);
    }-*/
    ;

    private native void log(Object obj)
        /*-{
            console.log(obj)
        }-*/
    ;

    private native String toJson(Object obj)
        /*-{
            return JSON.stringify(obj);
        }-*/
    ;

    @Override
    public ApplicationListener createApplicationListener() {
        final WebSocket webSocket = getWebSocket("ws://localhost:8090/ws");
        Started started = new Started(new InputStateImpl());
        started.setMessageSender(message -> {
            webSocket.send(toJson(message));
        });
        Timer timer = new Timer() {
            @Override
            public void run() {
                started.handleTimer();
            }
        };
        timer.scheduleRepeating(1000 / 1);

        final AtomicBoolean once = new AtomicBoolean(false);
        EventListenerCallback callback = new EventListenerCallback() {
            @Override
            public void callEvent(WsEvent wsEvent) {
                if (!once.get()) {
                    webSocket.send("hello");
                    once.set(true);
                }
                log(wsEvent.getData());
            }
        };


        webSocket.addEventListener("open", callback);
        webSocket.addEventListener("close", callback);
        webSocket.addEventListener("error", callback);
        webSocket.addEventListener("message", callback);
        return started;
    }
}
