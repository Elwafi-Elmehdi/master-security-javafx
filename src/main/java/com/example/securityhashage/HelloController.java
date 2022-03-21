package com.example.securityhashage;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextArea message;
    @FXML
    private TextField hashCode;
    @FXML
    private ComboBox algoChoise;

    @FXML
    public void md5Hashing(){
        try{
            String algo = algoChoise.getValue().equals(AlgoType.MD5) ? "MD5" : "SHA-1";
            MessageDigest md = MessageDigest.getInstance(algo);
            byte[] hashInBytes = md.digest(message.getText().getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (Byte b : hashInBytes){
                sb.append(String.format("%x", b));
            }
            hashCode.setText(sb.toString());
            System.out.println(sb);
        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        algoChoise.getItems().setAll(AlgoType.values());
        algoChoise.setValue(AlgoType.MD5);
    }
}