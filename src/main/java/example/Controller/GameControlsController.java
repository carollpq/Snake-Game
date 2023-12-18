package example.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.io.IOException;
/**
 * Controller class for the game controls selection frame in the Snake Game.
 * Extends the StartFrameController. Handles user interactions and events related
 * to choosing between left-hand and right-hand controls for the Snake game.
 *
 * @author Carolina Lee Pei Qian
 * @version 1.0
 */

public class GameControlsController extends StartFrameController {

    @FXML
    private RadioButton leftHandControls, rightHandControls;
    private ToggleGroup controlsToggleGroup; //Makes sure only one button is selected
    private String controlsChosen;

    /**
     * Initializes the game controls selection frame.
     * Creates a ToggleGroup and assigns it to the RadioButtons to ensure
     * that only one button is selected at a time.
     */
    @FXML
    public void initialize() {
        // Create a ToggleGroup and assign it to the RadioButtons
        controlsToggleGroup = new ToggleGroup();
        leftHandControls.setToggleGroup(controlsToggleGroup);
        rightHandControls.setToggleGroup(controlsToggleGroup);
    }

    /**
     * Handles the selection of a radio button. Sets the chosen controls based on
     * the selected radio button (left-hand or right-hand controls).
     */
    @FXML
    private void handleRadioButtonSelection() {
        if (controlsToggleGroup.getSelectedToggle() == leftHandControls) {
            setLeftHandControls();
        } else if (controlsToggleGroup.getSelectedToggle() == rightHandControls) {
            setRightHandControls();
        }
    }

    /**
     * Gets the chosen controls preference.
     *
     * @return The chosen controls preference ("left" or "right").
     */
    public String getControlsChosen() {
        return controlsChosen;
    }

    /**
     * Sets the controls preference to the specified value.
     *
     * @param controlsChosen The controls preference to set ("left" or "right").
     */
    public void setControlsChosen(String controlsChosen) {
        this.controlsChosen = controlsChosen;
    }

    @FXML
    /**
     * Sets the controls to 'W', 'A', 'S', 'D' in Snake and updates the
     * controls preference to "left".
     */
    public void setLeftHandControls() {
        setControlsChosen("left");
        MyFrameController.setControls("left");
    }

    @FXML
    /**
     * Sets the controls to 'UP', 'DOWN', 'LEFT', 'RIGHT' in Snake and updates
     * the controls preference to "right".
     */
    public void setRightHandControls() {
        setControlsChosen("right");
        MyFrameController.setControls("right");
    }

    @FXML
    /**
     * Checks whether the player has chosen the control preference.
     * If not, displays an error alert. Otherwise, switches to the game mode screen.
     *
     * @throws IOException If an I/O error occurs during the transition to the game mode.
     */
    public void checkChosenControls() throws IOException {
        if (getControlsChosen() == null) {
            showAlert();
        } else {
            switchToGameMode();
        }
    }

    /**
     * Displays an error alert informing the player to choose their preferred controls.
     */
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Please choose your preferred controls.");
        alert.showAndWait();
    }
}
