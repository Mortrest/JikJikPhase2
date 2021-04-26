package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;

public class ChatComponentController {
    @FXML
    public MenuItem edit;
    @FXML
    public Circle circle;
    @FXML
    private Pane TweetPane111;

    @FXML
    private Label name;


    @FXML
    private Label text;

    @FXML
    private MenuItem delete;



    public MenuItem getDelete(){
        return delete;
    }

    public MenuItem getEdit(){
        return edit;
    }


    public Circle getCircle() {
        return circle;
    }

    public Label getName() {
        return name;
    }

    public Label getText() {
        return text;
    }
    public double getWidth(){
        return name.getWidth();
    }
    public void setName(String str){
        name.setText(str);
    }
    public void setText(String str){
        text.setText(str);
    }
}
