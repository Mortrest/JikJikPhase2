package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class TweetComponentController {

    public Pane getIdPane() {
        return idPane;
    }

    @FXML
    private Pane idPane;

    @FXML
    private Label nameLabel;

    @FXML
    private Label tweetLabel;


    public void setNameLabel(String str){
        nameLabel.setText(str);
    }
    public void setTweetLabel(String str){
        tweetLabel.setText(str);
    }

}
