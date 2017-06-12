package sample.connectors;


import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.websocket.*;
import java.io.*;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.sql.Blob;
import java.util.Base64;

/**
 * Created by Lenovo on 12.06.2017.
 */

@ClientEndpoint
public class UDPConnection {

    private WebSocketContainer container;
    private Session session;

    public UDPConnection()  {
         container = ContainerProvider.getWebSocketContainer();
    }

    public void connect(){
        try {
            URI uri = URI.create("ws://localhost:8080/myHandler");
            container.connectToServer(this, uri);
        } catch ( IOException ex) {
            ex.printStackTrace();
            System.exit(-1);
        } catch (DeploymentException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image to Send");
        File file = fileChooser.showOpenDialog(stage);


        try {
            FileChannel input = new FileInputStream(file).getChannel();
            OutputStream output = session.getBasicRemote().getSendStream();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int read;
            while (input.read(buffer) > 0) {
                buffer.flip();
                session.getBasicRemote().sendBinary(buffer);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("SessionID: " + session.getId());

    }


        @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.session = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.session = null;
    }

    public void sendMessage(String message) {
        this.session.getAsyncRemote().sendText(message);
    }

}
