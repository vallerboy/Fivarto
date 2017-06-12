package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.connectors.UDPConnection;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Button buttonConnect;
    @FXML
    Button buttonRecord;
    @FXML
    Label infoText;

    private UDPConnection udpConnection;

    public Controller() {

        udpConnection = new UDPConnection();
        udpConnection.connect();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buttonConnect.setOnMouseClicked(e -> {
            udpConnection.sendMessage((Stage)((Button)e.getSource()).getScene().getWindow());
           // udpConnection.sendMessage("Witaj!");
            System.out.println("Kliknieto!");

        });

    }
}
