package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class ChatComponentController {

    @FXML
    private Pane TweetPane111;

    @FXML
    private Label name;

    @FXML
    private Label text;



    public void setName(String str){
        name.setText(str);
    }
    public void setText(String str){
        text.setText(str);
    }
}
