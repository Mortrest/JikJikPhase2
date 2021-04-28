package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class FollowerComponentController {
    @FXML
    public Pane pane;
    @FXML
    public Rectangle rect,rect1;
    @FXML
    private Label name;
    public Label getName() {
        return name;
    }

    public void setName(String str) {
        name.setText(str);
    }

    public Pane getPane() {
        return pane;
    }

    public Rectangle getRect() {
        return rect;
    }
}
