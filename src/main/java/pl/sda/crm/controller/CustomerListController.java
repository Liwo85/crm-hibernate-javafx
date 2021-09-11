package pl.sda.crm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CustomerListController {

    private SceneLoader sceneLoader;

    public CustomerListController() {
        this.sceneLoader = new SceneLoader();
    }

    @FXML
    public void goBack(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("personRegistration.fxml", event);
    }
    @FXML
    public void backToMenu(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("home.fxml", event);
    }
}
