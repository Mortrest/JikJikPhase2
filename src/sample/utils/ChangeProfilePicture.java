package sample.utils;

import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Models.User;
import sample.Models.Users;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ChangeProfilePicture {
    public ChangeProfilePicture(TextField idPane) throws IOException {
        FileChooser fileChooser = new FileChooser();
        Stage window = (Stage) idPane.getScene().getWindow();
        File file = fileChooser.showOpenDialog(window);
        File file1 = new File("C:\\Users\\ali80\\Desktop\\Tokyo\\Phase2\\src\\sample\\images\\" + Users.getCurrentUser().getUsername() + ".jpg");
        Files.copy(file.toPath(),file1.toPath());
        Users.getCurrentUser().setProfilePic(file1.toString());
    }
}
