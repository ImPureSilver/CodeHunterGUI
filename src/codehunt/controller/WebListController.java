package codehunt.controller;

import codehunt.model.CodeHuntEngine;
import codehunt.model.banks.WebsiteBank;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class WebListController {

    @FXML
    private TextField textBox;

    @FXML
    private ListView<String> websiteListView;

    private CodeHuntEngine engine = new CodeHuntEngine();

    // Called from the Main Menu to automatically fill the ListView with
    // the users sites from previous sessions.
    public void load() {

        // My instance variables from CodeHuntEngine and WebsiteBank
        WebsiteBank websiteBank = new WebsiteBank();

        websiteBank.readWebsites();
        if (this.websiteListView.getItems().size() == websiteBank.pullSites().size()) return;


        System.out.println("Loading Data start!");
        for (String website : websiteBank.pullSites()) {

            this.websiteListView.getItems().add(website);
        }

        this.websiteListView.refresh();
    }


    @FXML
    void save() {

        // Get all the Strings in the ListView and save them to CodeHunter
        for (String website : this.websiteListView.getItems()) {
            this.engine.addSite(website);
        }

        engine.saveState();
    }

    @FXML
    void saveSiteToList(KeyEvent event) {

        /*
         * If the key that is pressed is the ENTER KEY,
         * take the text from the textBox and pass it into
         * CodeHunter and to the ListView to present it to
         * the user.
         */

        String website = this.textBox.getText();

        // For adding in websites
        if (website != null) {
            if (event.getCode().equals(KeyCode.ENTER)) {
                this.websiteListView.getItems().add(website);
                this.textBox.clear();
            }
        }

        // Refresh the list after adding in a new site
        this.websiteListView.refresh();
    }

    @FXML
    void deleteItem(KeyEvent event) {

        String item = this.websiteListView.getSelectionModel().getSelectedItem();

        if (item != null) {
            if ((event.getCode().equals(KeyCode.DELETE) || (event.getCode().equals(KeyCode.BACK_SPACE)))) {
                this.websiteListView.getItems().remove(item);
                this.engine.removeSite(item);
            }
        }
    }

}
