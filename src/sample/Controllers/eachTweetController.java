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
import javafx.stage.Stage;
import sample.Models.Tweet;
import sample.Models.Tweets;
import sample.Models.Users;

import java.io.IOException;
import java.util.LinkedList;

public class eachTweetController {
    @FXML
    public GridPane grid;
    @FXML
    private TextArea textArea;
    @FXML
    private Button backBtn;
    public void initialize() throws IOException {
        System.out.println(Tweets.getTweetID());
        LinkedList<Tweet> tw = Users.getTweets().getComments(Tweets.getTweetID());
        System.out.println(tw.size() + "Hello");
        for (int i = 1; i < tw.size()+1; i++) {
            System.out.println(tw);
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

    public void tweetPage(String ID) throws IOException {
        Tweets.setTweetID(ID);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("../FXML/sample.fxml"));
        Stage window = (Stage) grid.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void back() throws IOException {
        if (Tweets.search(Tweets.getTweetID()).getParent().equals("0")){
            Tweets.setTweetID(null);
        } else {
            Tweets.setTweetID(Tweets.search(Tweets.getTweetID()).getParent());
        }
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("../FXML/sample.fxml"));
        Stage window = (Stage) grid.getScene().getWindow();
        window.setScene(new Scene(root));

    }
}
