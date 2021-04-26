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

public class HomePageController {
    @FXML
    public GridPane grid;
    @FXML
    private TextArea textArea;

    public void initialize() throws IOException {
        loadData();
    }

    //Adding tweet
    public void addTweet() throws IOException {
        Users.makeTweet(Users.getCurrentUser(),textArea.getText());
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../FXML/TweetComponent.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        TweetComponentController itemController = fxmlLoader.getController();
        itemController.setTweetLabel(textArea.getText());
        grid.add(anchorPane, 1, grid.getRowCount() + 1);
        GridPane.setMargin(anchorPane, new Insets(10));
        textArea.setText("");
    }

    public void loadData() throws IOException {
        new TweetLoad(grid, textArea,3);
    }

}
