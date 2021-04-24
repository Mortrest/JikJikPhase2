package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class A {
    @FXML
    private Button hiButton,byeButton;

    public void loadSecond() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/2.fxml"));
        Stage window = (Stage) hiButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void loadFirst()throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/1.fxml"));
        Stage window = (Stage)  byeButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
