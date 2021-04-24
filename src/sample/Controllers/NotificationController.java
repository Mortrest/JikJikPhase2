package sample.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Models.Chat;
import sample.Models.Notif;
import sample.Models.Room;
import sample.Models.Users;

import java.io.IOException;
import java.util.LinkedList;

public class NotificationController {

    @FXML
    private GridPane grid;

    public void initialize() throws IOException {
        LinkedList<Notif> tw = Users.getNotifs().showNotif(Users.getCurrentUser().getUsername());
        for (int i = 0; i < tw.size(); i++) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("../FXML/NotificationComponent.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
//            anchorPane.setId("pane" + i);
            NotificationComponentController itemController = fxmlLoader.getController();
            itemController.setName(tw.get(i).getOwner());
            itemController.setText(tw.get(i).getText());
            if (tw.get(i).getType().equals("1")){
                itemController.hide();
            }
            grid.add(anchorPane, 1, grid.getRowCount() + 1);
            System.out.println(grid.getRowCount());
            grid.setLayoutX(-40);
            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }


}