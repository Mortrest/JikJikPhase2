package sample.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CustomTweetComponent extends AnchorPane {
    public CustomTweetComponent() throws IOException {
        super();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TweetComponent.fxml"));
        Node n = fxmlLoader.load();
        this.getChildren().add(n);

    }
}
