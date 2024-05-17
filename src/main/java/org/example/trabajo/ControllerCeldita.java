package org.example.trabajo;

import javafx.stage.Stage;

public class ControllerCeldita {
    private ParameterDataModelProperties model;
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setModel(ParameterDataModelProperties model) {
        this.model = model;
    }
}
