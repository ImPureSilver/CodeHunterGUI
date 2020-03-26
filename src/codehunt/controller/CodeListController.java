package codehunt.controller;

import codehunt.model.banks.CodeBank;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CodeListController {

    @FXML
    private ListView<String> foundCodesListView;

    @FXML
    private Text totalCodesFound;

    private CodeBank bank = new CodeBank();

    @FXML
    void copy2Clipboard(MouseEvent event) {

        String item = this.foundCodesListView.getSelectionModel().getSelectedItem();

        if (item != null) {

            if (event.getClickCount() > 0) {
                // Take item and copy it to clipboard
                StringSelection stringSelection = new StringSelection(item);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

                this.totalCodesFound.setText("COPIED: " + item);
            }
        }
    }

    @FXML
    void updateCodeData() {

        this.bank.readCodes();
        if (this.foundCodesListView.getItems().size() == this.bank.totalCodes()) {
            return;
        }

        bank.readCodes();
        for (String code : bank.pullCodes()) {
            this.foundCodesListView.getItems().add(code);

        }
        this.totalCodesFound.setText("C O D E S - F O U N D : " + bank.totalCodes());

    }

}
