package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class MainController {
    @FXML
    private Label lbResult;

    @FXML
    public void btnClick(ActionEvent event){
        Button btn = (Button) event.getSource();
        String key = btn.getText();
        String number = lbResult.getText();
        lbResult.setText(Calculator.calculated(key, number));
    }

    @FXML
    public void keyPressed (javafx.scene.input.KeyEvent event){
        String number = lbResult.getText();
        if (new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN).match(event)){
            lbResult.setText(Calculator.calculated("+", number));
        }
        else if (new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN).match(event)) {
            lbResult.setText(Calculator.calculated("*", number));
        }
        else if (new KeyCodeCombination(KeyCode.BACK_SPACE).match(event)) {
            lbResult.setText(Calculator.calculated("del", number));
        }
        else{
            String key = event.getText();
            lbResult.setText(Calculator.calculated(key, number));
        }
    }
}