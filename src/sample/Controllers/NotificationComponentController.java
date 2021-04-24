package sample.Controllers;

        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.Pane;

public class NotificationComponentController {

    @FXML
    private Pane TweetPane111;

    @FXML
    private Label name;

    @FXML
    private Label text;

    @FXML
    private Button accept;

    @FXML
    private Button decline;

    public void setName(String str){
        name.setText(str);
    }
    public void setText(String str){
        text.setText(str);
    }
    public void hide(){
        accept.setVisible(false);
        decline.setVisible(false);
    }

}
