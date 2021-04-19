package sample.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.Models.*;
import java.io.IOException;

public class LogInController {
    @FXML
    private AnchorPane homePage,explorePage,chatPage,currentPage;
    @FXML
    private PasswordField signInPassword;
    @FXML
    private TextField signInTextField;
    @FXML
    private Label incorrect;
    @FXML
    private Button signInBtn,btnT1;


    public void initialize(){
        currentPage = homePage;
    }


    public void SignIn() throws IOException {
        if (signInPassword.getText().equals("1")){
            Users.setCurrentUser(Users.searchUsername(signInTextField.getText()));
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/sample.fxml"));
            Stage window = (Stage) signInBtn.getScene().getWindow();
            window.setScene(new Scene(root,1069, 673));

        } else {
            incorrect.setVisible(true);
            signInPassword.setText("");
        }

    }
    public void setHomePage(){
        currentPage.setVisible(false);
        homePage.setVisible(true);
        currentPage = homePage;

    }

    public void setExplorePage() {
        currentPage.setVisible(false);
        explorePage.setVisible(true);
        currentPage = explorePage;
    }

    public void setChatPage(){
        currentPage.setVisible(false);
        chatPage.setVisible(true);
        currentPage = chatPage;
    }
}
