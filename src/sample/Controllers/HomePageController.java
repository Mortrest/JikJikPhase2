package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import sample.ModelLoader;
import sample.Models.*;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class HomePageController {
    public Pane TweetPane;
    public AnchorPane Salam;
    @FXML
    private Button btnT1,btnT2,btnT3,tweetBtn,btnT4;
    @FXML
    public GridPane grid;
    @FXML
    private TextArea textArea;

    public void initialize() throws IOException {
        LinkedList<Tweet> tw = Users.getTweets().showTweetOwnPage(Users.class,Users.getCurrentUser().getUsername(), 3);
            for (int i = 1; i < tw.size()+1; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("../FXML/TweetComponent.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                TweetComponentController itemController = fxmlLoader.getController();
                int finalI = i-1;
                itemController.getIdPane().setOnMouseClicked(e -> {
                    try {
                        tweetPage(tw.get(finalI).getID());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                });
                itemController.setNameLabel(tw.get(i-1).getOwner());
                itemController.setTweetLabel(tw.get(i-1).getText());
                grid.add(anchorPane,1,grid.getRowCount()+1);
                GridPane.setMargin(anchorPane, new Insets(10));
            }
    }
    //Adding tweet
    public void addTweet() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../FXML/TweetComponent.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        TweetComponentController itemController = fxmlLoader.getController();
        itemController.setTweetLabel(textArea.getText());
        grid.add(anchorPane,1,grid.getRowCount()+1);
        GridPane.setMargin(anchorPane, new Insets(10));
        textArea.setText("");
    }

    public void tweetPage(String ID) throws IOException {
        Tweets.setTweetID(ID);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("../FXML/sample.fxml"));
        Stage window = (Stage) grid.getScene().getWindow();
        window.setScene(new Scene(root));

    }


}
