package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;

public class ChatComponentController {

    public MenuItem edit;
    @FXML
    private Pane TweetPane111;

    @FXML
    private Label name;

    @FXML
    private Label text;

    @FXML
    private MenuItem delete;



    public MenuItem setDelete(){
        return delete;
    }

    public MenuItem setEdit(){
        return edit;
    }

    public void setName(String str){
        name.setText(str);
    }
    public void setText(String str){
        text.setText(str);
    }
}
