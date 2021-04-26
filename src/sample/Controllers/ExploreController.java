package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Models.Users;
import sample.utils.TweetLoad;

import java.io.IOException;

public class ExploreController {
    @FXML
    public GridPane grid;
    public TextArea textArea;
    public void initialize() throws IOException {
        loadData();
        grid.setLayoutX(-40);
    }

    //Adding tweet

    public void loadData() throws IOException {
        new TweetLoad(grid, textArea,1);
    }

}
