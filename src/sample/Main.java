package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Models.Chats;
import sample.Models.Notifs;
import sample.Models.Tweets;
import sample.Models.Users;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        ModelLoader m = new ModelLoader();
        Tweets tweets = new Tweets(m);
        Notifs notifs = new Notifs(m);
        Chats chats = new Chats(m);
        Users users = new Users(m,tweets,chats,notifs);

        Parent root = FXMLLoader.load(getClass().getResource("FXML/SignIn.fxml"));
        primaryStage.setTitle("JikJik");
        primaryStage.setScene(new Scene(root, 1069, 673));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
