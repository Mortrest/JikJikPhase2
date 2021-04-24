package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class RoomProfileComponentController {
    @FXML
    private Label name;
    @FXML
    private Pane pane;
    @FXML
    private Label lastMsg;


    public void setNameLabel(String str){
        name.setText(str);
    }
    public void setLastMsg(String str){
        lastMsg.setText(str);
    }


    public Pane getPane() {
        return pane;
    }
}
