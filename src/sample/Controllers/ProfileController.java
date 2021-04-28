package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sample.Models.Tweets;
import sample.Models.User;
import sample.Models.Users;
import sample.utils.ChangeScene;
import sample.utils.TweetLoad;

import java.io.IOException;

public class ProfileController {

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
    @FXML
    private TextArea textArea,overlayText;
    @FXML
    private Pane overlay;


    public void initialize() throws IOException {
        User user = Users.getProfile();
        username.setText("@" + user.getUsername());
        if (user.getInfo() != null) {
            bio.setText(user.getInfo());
        } else {
            bio.setText("No bio");
        }
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
        loadData();
            grid.setLayoutY(90);
            grid.setLayoutX(-40);
        }

    public void back() throws IOException {
        Users.setProfile(Users.getCurrentUser());
        new ChangeScene("../FXML/sample.fxml",grid);
    }

    public void loadData() throws IOException {
        new TweetLoad(grid,textArea,2,overlay).load();
    }

    public void closeOverlay(){
        overlay.setVisible(false);
    }

    public void sendComment() throws IOException {
        if (overlayText.getText() != null){
            overlay.setVisible(false);
            Tweets.makeTweet(overlayText.getText(),Tweets.getComment(),Users.getCurrentUser().getUsername(),Users.getCurrentUser().getFollowers());
            grid.getChildren().clear();

            loadData();
        }
    }
}

