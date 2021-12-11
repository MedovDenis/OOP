import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class MainController {
    @FXML
    private TextField tfResult;
    private enum StateOperation{ StateNone, StatePlus, StateMinus, StateMul, StateDel, StatePow, StateSqrt }
    private StateOperation stateOperation;
    private boolean stateInput = false;
    private double oldNumber = 0;
    {
        stateOperation = StateOperation.StateNone;
    }

    @FXML
    public void btnClick(ActionEvent event) throws IOException {
        Object obj = event.getSource();
        if (!(obj instanceof Button btn)) throw new IOException();

        String btnType = btn.getText();
        switch (btnType){
            case "+":
                inputOperation(StateOperation.StatePlus);
                break;
            case "-":
                if (!stateInput){
                    tfResult.setText("-");
                    stateInput = true;
                }
                else inputOperation(StateOperation.StateMinus);
                break;
            case "*":
                inputOperation(StateOperation.StateMul);
                break;
            case "/":
                inputOperation(StateOperation.StateDel);
                break;
            case "pow":
                inputOperation(StateOperation.StatePow);
                break;
            case "sqrt":
                inputOperation(StateOperation.StateSqrt);
                break;
            case "CE":
                stateOperation = StateOperation.StateNone;
                stateInput = false;
                oldNumber = 0;
                tfResult.setText("0");
                break;
            case "=":
                String number = tfResult.getText();
                if (isNumber(number))
                    tfResult.setText(calcValue(number));
                else {
                    stateOperation = StateOperation.StateNone;
                    tfResult.setText("Error");
                }
                stateInput = false;
                break;
            case ".":
                if (!stateInput) {
                    tfResult.setText("0.");
                    stateInput = true;
                }
                else tfResult.setText(tfResult.getText() + ".");
                break;
            default:
                if (btnType.length() == 1 && Character.isDigit(btnType.charAt(0)))
                    inputNumber(btnType);
                else
                    tfResult.setText("Error");
                break;
        }
    }

    private void inputNumber (String digit){
        if (!stateInput) {
            tfResult.setText(digit);
            stateInput = true;
        }
        else tfResult.setText(tfResult.getText() + digit);
    }

    private void inputOperation (StateOperation operation){
        String number = tfResult.getText();
        String result = null;

        if (isNumber(number)) {
            if (stateOperation != StateOperation.StateNone) tfResult.setText(calcValue(number));
            if (operation == StateOperation.StateSqrt){
                stateOperation = operation;
                tfResult.setText(calcValue(number));
                stateOperation = StateOperation.StateNone;
            }
            else stateOperation = operation;

            if (!tfResult.getText().equals("Error"))
                oldNumber = getNumber(number);
            else
                oldNumber = 0;
        }
        else tfResult.setText("Error");

        stateInput = false;
    }

    private String calcValue(String number){
        double num = getNumber(number);
        switch (stateOperation){
            case StatePlus:
                return String.valueOf(oldNumber + num);
            case StateMinus:
                return String.valueOf(oldNumber - num);
            case StateMul:
                return String.valueOf(oldNumber * num);
            case StateDel:
                if (num != 0.0)
                    return String.valueOf(oldNumber / num);
                break;
            case StatePow:
                return String.valueOf(Math.pow(oldNumber, num));
            case StateSqrt:
                if (num >= 0)
                    return String.valueOf(Math.sqrt(num));
                break;
        }
        return "Error";
    }

    private double getNumber (String number){
        boolean dot = false;
        StringBuilder stringBuilder = new StringBuilder();
        for(char symbol : number.toCharArray()){
            if (symbol == '.' && !dot){
                stringBuilder.append(symbol);
                dot = true;
            }
            else if (symbol != '.') stringBuilder.append(symbol);
        }
        return Double.parseDouble(stringBuilder.toString());
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == '-' || str.charAt(i) == 'E')) return false;
        }
        return true;
    }
}