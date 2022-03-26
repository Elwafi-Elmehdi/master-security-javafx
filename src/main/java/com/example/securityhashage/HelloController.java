package com.example.securityhashage;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private TextArea message;
    @FXML
    private TextField hashCode;
    @FXML
    private ComboBox algoChoise;
    @FXML
    private Button genererBtn;
    @FXML
    private TextField msgCopier;
    @FXML
    private TextField hash2;
    @FXML
    private Label resultat;


    @FXML
    public void md5Hashing(ActionEvent actionEvent){
        try{
            if (actionEvent.getSource() == genererBtn){
                var str = generateHashCode(message.getText());
                hashCode.setText(str);

            }else{
                var str = generateHashCode(msgCopier.getText());
                hash2.setText(str);
            }

        }catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    public void copyText(ActionEvent actionEvent) {
        this.msgCopier.setText(message.getText());
    }

    @FXML
    public void verify(ActionEvent actionEvent) {
        if (hashCode.getText().equalsIgnoreCase(hash2.getText())){
            resultat.setTextFill(Color.GREEN);
            resultat.setText("LE MESSAGE EST INTEGRE");
        }else
        {
            resultat.setTextFill(Color.RED);
            resultat.setText("LE MESSAGE EST NON INTEGRE");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        algoChoise.getItems().setAll(AlgoType.values());
        algoChoise.setValue(AlgoType.MD5);
    }



    private String generateHashCode(String message) throws NoSuchAlgorithmException {
        String algo = algoChoise.getValue().equals(AlgoType.MD5) ? "MD5" : "SHA-1";
        MessageDigest md = MessageDigest.getInstance(algo);
        byte[] hashInBytes = md.digest(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        for (Byte b : hashInBytes){
            sb.append(String.format("%x", b));
        }
        return sb.toString();
    }


}