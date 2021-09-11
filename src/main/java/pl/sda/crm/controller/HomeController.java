package pl.sda.crm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

    public class HomeController {
    private SceneLoader sceneLoader;

    public HomeController() {
        this.sceneLoader = new SceneLoader();
    }

    @FXML
    public void displayPersonRegistration(ActionEvent event) throws IOException {
        sceneLoader.loadOnEvent("personRegistration.fxml", event);
    }
    @FXML
        public void displayCompanyRegistration(ActionEvent event) throws IOException {

            sceneLoader.loadOnEvent("companyRegistration.fxml", event);
        }

}
