package example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;

public class GameControlsController extends StartFrameController {

    @FXML
    private RadioButton leftHandControls, rightHandControls;
    private ToggleGroup controlsToggleGroup; //Makes sure only one button is selected
    private String controlsChosen;

    @FXML
    public void initialize() {
        // Create a ToggleGroup and assign it to the RadioButtons
        controlsToggleGroup = new ToggleGroup();
        leftHandControls.setToggleGroup(controlsToggleGroup);
        rightHandControls.setToggleGroup(controlsToggleGroup);
    }

    @FXML
    private void handleRadioButtonSelection() {
        if (controlsToggleGroup.getSelectedToggle() == leftHandControls) {
            setLeftHandControls();
        } else if (controlsToggleGroup.getSelectedToggle() == rightHandControls) {
            setRightHandControls();
        }
    }

    public String getControlsChosen() {
        return controlsChosen;
    }

    public void setControlsChosen(String controlsChosen) {
        this.controlsChosen = controlsChosen;
    }

    @FXML
    //Sets the controls to 'W', 'A', 'S', 'D' in MySnake
    public void setLeftHandControls() {
        setControlsChosen("left");
        MyFrameController.setControls("left");
    }

    @FXML
    //Sets the controls to 'UP', 'DOWN', 'LEFT', 'RIGHT' in MySnake
    public void setRightHandControls() {
        setControlsChosen("right");
        MyFrameController.setControls("right");
    }

    @FXML
    //Checks whether player has chosen the control preference
    public void checkChosenControls() throws IOException {
        if (getControlsChosen() == null) {
            showAlert();
        } else {
            switchToGameMode();
        }
    }

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please choose your preferred controls.");
        alert.showAndWait();
    }
}
