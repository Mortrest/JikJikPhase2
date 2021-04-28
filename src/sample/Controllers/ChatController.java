package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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

public class ChatController {
    @FXML
    public TextArea overlayText;
    @FXML
    private DialogPane overlay;
    @FXML
    private Button backBtn, submit, closeBtn;
    @FXML
    private GridPane grid;
    @FXML
    private Label fName, lastSeen;
    @FXML
    private TextArea textArea;

    public void initialize() throws IOException {
        overlay.setVisible(false);
        loadData();
    }

    public void back() throws IOException {
        overlay.setVisible(false);
        new ChangeScene("../FXML/sample.fxml", grid);
    }

    public void addChat() throws IOException {
        Users.getChats().makeChat(textArea.getText(), Users.getCurrentUser().getUsername(), Chats.getRoomID());
        LoadComponent loadComponent = new LoadComponent("../FXML/chatComponent.fxml");
        AnchorPane anchorPane = loadComponent.loadAnchor();
        ChatComponentController item = loadComponent.loadFxml().getController();
        item.setName(Users.getCurrentUser().getUsername());
        item.setText(textArea.getText());
//        item.getDelete().setOnAction(e -> delete());
        textArea.setText("");
        ownChat(anchorPane, item);
        GridPane.setMargin(anchorPane, new Insets(-15));
    }

    public void ownChat(AnchorPane anchorPane, ChatComponentController item) {
        item.getCircle().setLayoutX(item.getCircle().getLayoutX() + 895);
        Label text = item.getText();
        Label name = item.getName();
        text.setLayoutX(text.getLayoutX() + 700);
        if (text.getLayoutX() + text.getText().length() * 4 > 878) {
            int len = text.getText().length() - "salam iman".length();
            text.setLayoutX(text.getLayoutX() - len * 4 - 10);
        }
        if (text.getLayoutX() + text.getText().length() * 4 < 878) {
            int len = text.getText().length() - "salam iman".length();
            text.setLayoutX(text.getLayoutX() - len * 4 + 10);
        }
        name.setLayoutX(name.getLayoutX() + 700);
        grid.add(anchorPane, 1, grid.getRowCount() + 1);
        grid.setLayoutX(-40);
    }

    public void loadData() throws IOException {
        grid.getChildren().clear();
        Room room = Users.getChats().searchRoomID(Chats.getRoomID());
        if (room != null) {
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
            fxmlLoader.setLocation(getClass().getResource("../FXML/chatComponent.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            ChatComponentController itemController = fxmlLoader.getController();
            itemController.setName(tw.get(i).getOwner());
            itemController.setText(tw.get(i).getText());
            int finalI = i;
            itemController.getEdit().setOnAction(e -> {
                try {
                    edit(tw.get(finalI).getID());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            itemController.getDelete().setOnAction(e -> {
                try {
                    delete(tw.get(finalI).getID());
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
            if (tw.get(i).getOwner().equals(Users.getCurrentUser().getUsername())) {
                ownChat(anchorPane, itemController);
            } else {
                grid.add(anchorPane, 1, grid.getRowCount() + 1);
                grid.setLayoutX(-40);
                grid.setLayoutY(20);
            }
            GridPane.setMargin(anchorPane, new Insets(-15));
        }
    }


    public void delete(String ID) throws IOException {
        Chats.deleteChat(ID);
        loadData();
    }

    public void edit(String ID) throws IOException {
        overlayText.setText(Chats.searchChat(ID).getText());
        Chats.setEditID(ID);
        overlay.setVisible(true);
    }


    public void editBtn() throws IOException {
        Chats.editChat(Chats.getEditID(), overlayText.getText());
        closeOverlay();
        loadData();
    }

    public void closeOverlay() {
        overlay.setVisible(false);
        overlayText.setText("");
        Chats.setEditID(null);
    }

}