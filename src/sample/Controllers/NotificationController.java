package sample.Controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Models.Notif;
import sample.Models.Users;
import sample.utils.LoadComponent;

import java.io.IOException;
import java.util.LinkedList;

public class NotificationController {

    @FXML
    private GridPane grid;

    public void initialize() throws IOException {
        LinkedList<Notif> tw = Users.getNotifs().showNotif(Users.getCurrentUser().getUsername());
        for (Notif notif : tw) {
            LoadComponent loadComponent = new LoadComponent("../FXML/NotificationComponent.fxml");
            AnchorPane anchorPane = loadComponent.loadAnchor();
            NotificationComponentController itemController = loadComponent.loadFxml().getController();
            itemController.setName(notif.getOwner());
            itemController.setText(notif.getText());
            if (notif.getType().equals("1")) {
                itemController.hide();
            }
            grid.add(anchorPane, 1, grid.getRowCount() + 1);
            grid.setLayoutX(-40);
            GridPane.setMargin(anchorPane, new Insets(10));
        }
    }


}