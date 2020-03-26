package codehunt.controller;

import codehunt.model.CodeHuntEngine;
import codehunt.model.banks.WebsiteBank;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CodeHuntController {

    private CodeHuntEngine engine = new CodeHuntEngine();

    @FXML
    void huntButtonAction() {

        WebsiteBank websiteBank = new WebsiteBank();
        this.engine.recoverSavedStuff();

        if (websiteBank.pullSites().isEmpty()) {
            return;
        }

        this.engine.pullAllCodesFromSites();

        this.engine.saveState();


    }

    // BUTTON BINDINGS BELOW
    @FXML
    void showCodeListWindow(MouseEvent event) {

        // Load the FXML for the Code List
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/CodeList.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("C O D E - H U N T E R: Code List");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void showWebListWindow(MouseEvent event) {

        // Load the FXML for the Website List
        try {

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../view/WebList.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("C O D E - H U N T E R: Website list");
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
