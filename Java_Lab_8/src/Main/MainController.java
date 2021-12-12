package Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private TextField tfResult;
    private enum StateOperation{ StateNone, StatePlus, StateMinus, StateMul, StateDel, StatePow, StateSqrt }
    private StateOperation stateOperation;
    private boolean stateInput = false;
    private double firstNumber = 0;
    {
        stateOperation = StateOperation.StateNone;
    }

    @FXML
    public void btnClick(ActionEvent event){
        Button btn = (Button) event.getSource();

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
                firstNumber = 0;
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

    public void keyPressed (javafx.scene.input.KeyEvent event){
        String key = event.getText();
        if (key.length() == 1){
            switch (key.charAt(0)){
                case ('.'):
                    if (!stateInput) {
                        tfResult.setText("0.");
                        stateInput = true;
                    }
                    else tfResult.setText(tfResult.getText() + ".");
                    break;
                case (','):
                    if (!stateInput) {
                        tfResult.setText("0,");
                        stateInput = true;
                    }
                    else tfResult.setText(tfResult.getText() + ",");
                    break;
                case ('-'):
                    if (!stateInput){
                        tfResult.setText("-");
                        stateInput = true;
                    }
                    break;
                default:
                    if(Character.isDigit(key.charAt(0))) inputNumber(key);
                    break;
            }
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

        if (isNumber(number)) {
            if (stateOperation != StateOperation.StateNone) tfResult.setText(calcValue(number));
            if (operation == StateOperation.StateSqrt){
                stateOperation = operation;
                tfResult.setText(calcValue(number));
                stateOperation = StateOperation.StateNone;
            }
            else stateOperation = operation;

            if (!tfResult.getText().equals("Error"))
                firstNumber = getNumber(number);
            else
                firstNumber = 0;
        }
        else tfResult.setText("Error");
        stateInput = false;
    }

    private String calcValue(String number){
        double secondNumber = getNumber(number);
        switch (stateOperation){
            case StatePlus:
                return String.valueOf(firstNumber + secondNumber);
            case StateMinus:
                return String.valueOf(firstNumber - secondNumber);
            case StateMul:
                return String.valueOf(firstNumber * secondNumber);
            case StateDel:
                if (secondNumber != 0.0)
                    return String.valueOf(firstNumber / secondNumber);
                break;
            case StatePow:
                return String.valueOf(Math.pow(firstNumber, secondNumber));
            case StateSqrt:
                if (secondNumber >= 0)
                    return String.valueOf(Math.sqrt(secondNumber));
                break;
            case StateNone:
                return String.valueOf(secondNumber);

        }
        return "Error";
    }

    private double getNumber (String number){
        boolean dot = false;
        StringBuilder stringBuilder = new StringBuilder();
        number = number.replace(',', '.');
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
            if (!(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == ',' || str.charAt(i) == '-' || str.charAt(i) == 'E')) return false;
        }
        return true;
    }
}