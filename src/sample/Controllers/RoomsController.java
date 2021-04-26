package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Models.Chat;
import sample.Models.Chats;
import sample.Models.Room;
import sample.Models.Users;
import sample.utils.ChangeScene;
import sample.utils.LoadComponent;

import java.io.IOException;
import java.util.LinkedList;

public class RoomsController {

    @FXML
    private GridPane grid;
    public void initialize() throws IOException {
        LinkedList<Room> tw = Users.getChats().userRoom(Users.getCurrentUser().getUsername());
        for (int i = 0; i < tw.size(); i++) {
            LoadComponent loadComponent = new LoadComponent("../FXML/roomProfileComponent.fxml");
            AnchorPane anchorPane = loadComponent.loadAnchor();
            RoomProfileComponentController itemController = loadComponent.loadFxml().getController();
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
        new ChangeScene("../FXML/chat.fxml",grid);
    }
}