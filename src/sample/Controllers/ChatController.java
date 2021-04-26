package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Models.*;
import sample.utils.ChangeScene;

import java.io.IOException;
import java.util.LinkedList;

public class ChatController {
    @FXML
    private Button backBtn,submit;
    @FXML
    private GridPane grid;
    @FXML
    private Label fName,lastSeen;
    @FXML
    private TextArea textArea;



    public void initialize() throws IOException {
        System.out.println(Chats.getRoomID());
        Room room = Users.getChats().searchRoomID(Chats.getRoomID());
        if (room!= null) {
//            if (room.getOwner1().equals(room.getOwner2())){
//                fName.setText("Saved Messages");
//            }
//            else if (room.getOwner1().equals(Users.getCurrentUser().getUsername())) {
//                fName.setText(room.getOwner2());
//            } else {
//                fName.setText(room.getOwner1());
//            }
//        }
        }
        LinkedList<Chat> tw = Users.getChats().showChats(Chats.getRoomID());
        for (int i = 0; i < tw.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            System.out.println(tw.get(i).getOwner() + " " + Users.getCurrentUser());
            fxmlLoader.setLocation(getClass().getResource("../FXML/chatComponent.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
//              anchorPane.setId("pane" + i);
            ChatComponentController itemController = fxmlLoader.getController();
            itemController.setName(tw.get(i).getOwner());
            itemController.setText(tw.get(i).getText());
            if (tw.get(i).getOwner().equals(Users.getCurrentUser().getUsername())){
                System.out.println("jooooooooooooo");
                grid.add(anchorPane, 1, grid.getRowCount() + 1);

            } else {
                grid.add(anchorPane, 1, grid.getRowCount() + 1);

            }

            grid.setLayoutX(-40);

            GridPane.setMargin(anchorPane, new Insets(-15));
        }
    }

    public void back() throws IOException {
        new ChangeScene("../FXML/sample.fxml",grid);
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        Parent root = fxmlLoader.load(getClass().getResource("../FXML/sample.fxml"));
//        Stage window = (Stage) grid.getScene().getWindow();
//        window.setScene(new Scene(root));
    }

    public void addChat() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("../FXML/chatComponent.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
//              anchorPane.setId("pane" + i);
        ChatComponentController itemController = fxmlLoader.getController();
        itemController.setName(Users.getCurrentUser().getUsername());
        itemController.setText(textArea.getText());
        grid.add(anchorPane, 1, grid.getRowCount() + 1);
        grid.setLayoutX(-40);
        Users.getChats().makeChat(textArea.getText(),Users.getCurrentUser().getUsername(),Chats.getRoomID());
        textArea.setText("");
        GridPane.setMargin(anchorPane, new Insets(-15));
    }
}