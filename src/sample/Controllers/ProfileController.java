package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Models.Tweet;
import sample.Models.Tweets;
import sample.Models.User;
import sample.Models.Users;
import sample.utils.ChangeScene;
import sample.utils.LoadComponent;

import java.io.IOException;
import java.util.LinkedList;

public class ProfileController {

    @FXML
    private AnchorPane Salam;

    @FXML
    private Label fNames;

    @FXML
    private Label username;

    @FXML
    private Label bio;

    @FXML
    private Label flwrsCount;

    @FXML
    private Label flwingCount;

    @FXML
    private Button followBtn;

    @FXML
    private Label isFollowing;

    @FXML
    private GridPane grid;



    public void initialize() throws IOException {
        User user = Users.getProfile();
        username.setText("@" + user.getUsername());
        bio.setText(user.getInfo());
        fNames.setText(user.getFirstName() + " " + user.getLastName());
        flwrsCount.setText(Integer.toString(user.getFollowers().size()));
        flwingCount.setText(Integer.toString(user.getFollowing().size()));
        if (user.getUsername().equals(Users.getCurrentUser().getUsername())){
            followBtn.setVisible(false);
            isFollowing.setText("It's you bro");
        } else {
            followBtn.setVisible(true);
            if (Users.getCurrentUser().getFollowers().contains(user.getUsername())){
                isFollowing.setText(user.getUsername() + " is Following You!");
            } else {
                isFollowing.setText(user.getUsername() + " isn't Following You!");
            }
            if (Users.getCurrentUser().getFollowing().contains(user.getUsername())){
                followBtn.setText("Unfollow");
            } else {
                followBtn.setText("Follow");
            }
        }

        LinkedList<Tweet> tw = Users.getTweets().showTweetOwnPage(Users.class,Users.getCurrentUser().getUsername(), 3);
        for (int i = 1; i < tw.size()+1; i++) {
            LoadComponent loadComponent = new LoadComponent("../FXML/TweetComponent.fxml");
            AnchorPane anchorPane = loadComponent.loadAnchor();
            TweetComponentController itemController = loadComponent.loadFxml().getController();
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
            grid.setLayoutY(anchorPane.getLayoutY()+90);
            grid.setLayoutX(-40);
            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }
    public void tweetPage(String ID) throws IOException {
        Tweets.setTweetID(ID);
        new ChangeScene("../FXML/sample.fxml",grid);

    }
}

