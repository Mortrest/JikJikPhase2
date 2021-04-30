package sample.utils;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import sample.Controllers.TweetComponentController;
import sample.Models.Tweet;
import sample.Models.Tweets;
import sample.Models.Users;
import java.io.IOException;
import java.util.LinkedList;

public class TweetLoad {
    GridPane grid;
    TextArea textArea;
    Pane overlay;
    int type;
    public TweetLoad(GridPane grid,TextArea textArea, int type,Pane overlay){
        this.grid = grid;
        this.type = type;
        this.textArea = textArea;
        this.overlay = overlay;

    }
    public void load() throws IOException {
        LinkedList<Tweet> tw = Users.getTweets().showTweetOwnPage(Users.class,Users.getCurrentUser().getUsername(), type);
        for (int i = 1; i < tw.size()+1; i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../FXML/TweetComponent.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            TweetComponentController itemController = fxmlLoader.getController();
            itemController.getProfilePic().setFill(new ImagePattern(new Image("/sample/images/iman.JPG")));
            int finalI = i-1;
            itemController.setTweetID(tw.get(i-1).getID());
            itemController.getLikeCount().setText(Integer.toString(tw.get(i-1).getLikes().size()));
            itemController.getRet().setOnMouseClicked(e -> {
                try {
                    retweet(tw.get(finalI).getID());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            itemController.getComment().setOnMouseClicked(e -> makeTweet(tw.get(finalI).getID()));
            itemController.getIdPane().setOnMouseClicked(e -> {
                try {
                    tweetPage(tw.get(finalI).getID());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            itemController.setNameLabel("@"+tw.get(i-1).getOwner()+" - " + Users.searchUsername(tw.get(i-1).getOwner()).getName());
            itemController.setTweetLabel(tw.get(i-1).getText());
            grid.add(anchorPane,1,grid.getRowCount()+1);
            GridPane.setMargin(anchorPane, new Insets(10));
        }

    }

    public void makeTweet(String ID){
        overlay.setVisible(true);
        Tweets.setComment(ID);
    }

    public void tweetPage(String ID) throws IOException {
        Tweets.setTweetID(ID);
        new ChangeScene("../FXML/sample.fxml",grid);

    }
    public void addTweet() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../FXML/TweetComponent.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        TweetComponentController itemController = fxmlLoader.getController();
        itemController.setTweetLabel(textArea.getText());
        grid.add(anchorPane, 1, grid.getRowCount() + 1);
        GridPane.setMargin(anchorPane, new Insets(10));
        textArea.setText("");
    }

    public void retweet(String ID) throws IOException {
        Tweets.reTweet(Tweets.search(ID),Users.getCurrentUser());
        grid.getChildren().clear();
        load();
    }
}
