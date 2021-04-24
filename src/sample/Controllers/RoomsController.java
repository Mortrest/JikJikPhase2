package sample.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Models.Chat;
import sample.Models.Chats;
import sample.Models.Room;
import sample.Models.Users;

import java.io.IOException;
import java.util.LinkedList;

public class RoomsController {

    @FXML
    private GridPane grid;

//    public void initialize() throws IOException {
//            FXMLLoader fxmlLoader = new FXMLLoader();
//            fxmlLoader.setLocation(getClass().getResource("../FXML/roomProfileComponent.fxml"));
//            AnchorPane anchorPane = fxmlLoader.load();
//            anchorPane.setId("3");
//            RoomProfileComponentController itemController = fxmlLoader.getController();
//            itemController.getPane().setOnMouseClicked(e -> System.out.println("bitch"));
////            if (tw.get(i ).getOwner1().equals(Users.getCurrentUser().getUsername())) {
////                itemController.setNameLabel(tw.get(i).getOwner2());
////            } else {
////                itemController.setNameLabel(tw.get(i ).getOwner1());
////
////            }
////            LinkedList<Chat> chat = Users.getChats().showChats(tw.get(i).getRoomID());
////            if (chat.size() != 0){
////                itemController.setLastMsg(chat.get(chat.size()-1).getText());
////            } else {
////                itemController.setLastMsg("No Messages!");
////            }
//            grid.add(anchorPane,1,grid.getRowCount()+1);
//            grid.setLayoutY(grid.getLayoutY()-4);
//            grid.setLayoutX(-60);
//            GridPane.setMargin(anchorPane, new Insets(10));
//
//    }



    public void initialize() throws IOException {
        LinkedList<Room> tw = Users.getChats().userRoom(Users.getCurrentUser().getUsername());

        System.out.println(tw.size());
        for (int i = 0; i < tw.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../FXML/roomProfileComponent.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            anchorPane.setId("pane"+i);
            RoomProfileComponentController itemController = fxmlLoader.getController();
            int finalI = i;


            itemController.getPane().setOnMouseClicked(e -> {




                try {
                    chatPage(tw.get(finalI).getRoomID());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            if (tw.get(i ).getOwner1().equals(Users.getCurrentUser().getUsername())) {
                itemController.setNameLabel(tw.get(i).getOwner2());
            } else {
                itemController.setNameLabel(tw.get(i ).getOwner1());

            }
            LinkedList<Chat> chat = Users.getChats().showChats(tw.get(i).getRoomID());
            if (chat.size() != 0){
                itemController.setLastMsg(chat.get(chat.size()-1).getText());
            } else {
                itemController.setLastMsg("No Messages!");
            }
            grid.add(anchorPane,1,grid.getRowCount()+1);
            grid.setLayoutY(grid.getLayoutY()-4);
            grid.setLayoutX(-60);
//            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }

    public void chatPage(String id) throws IOException {
        Chats.setRoomID(id);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("../FXML/chat.fxml"));
        Stage window = (Stage) grid.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}