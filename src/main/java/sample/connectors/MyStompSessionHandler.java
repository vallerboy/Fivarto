package sample.connectors;

import org.springframework.messaging.simp.stomp.*;

import java.lang.reflect.Type;

/**
 * Created by Lenovo on 12.06.2017.
 */
public class MyStompSessionHandler implements StompSessionHandler {

    private StompSession session;

    public StompSession getSession() {
        return session;
    }

    public void setSession(StompSession session) {
        this.session = session;
    }

    @Override
    public void afterConnected(StompSession stompSession, StompHeaders stompHeaders) {
        System.out.println("Polaczono");
        session = stompSession;

        session.subscribe("/answer", new StompFrameHandler() {

            @Override
            public Type getPayloadType(StompHeaders headers) {

                return String.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
               System.out.println("Hello: " +  payload);
            }

        });
    }

    @Override
    public void handleException(StompSession stompSession, StompCommand stompCommand, StompHeaders stompHeaders, byte[] bytes, Throwable throwable) {
        System.out.println("Polaczono1");
    }

    @Override
    public void handleTransportError(StompSession stompSession, Throwable throwable) {
        throwable.printStackTrace();

    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return null;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {

    }
}
