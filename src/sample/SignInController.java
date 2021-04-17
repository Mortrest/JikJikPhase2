package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class SignInController {
    public AnchorPane p2;
    public AnchorPane p1;
    @FXML
    private PasswordField signInPassword;
    @FXML
    private TextField signInTextField;

    @FXML
    private Button button;

    public void SignIn(ActionEvent e){
        p2.setVisible(true);
        p1.setVisible(false);
        System.out.println("lol");
    }

}
