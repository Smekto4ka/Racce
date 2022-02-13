package love.smekto4ka.client.ws;

import jsinterop.annotations.JsFunction;

@JsFunction
public interface EventListenerCallback {
    void callEvent(WsEvent wsEvent);
}
