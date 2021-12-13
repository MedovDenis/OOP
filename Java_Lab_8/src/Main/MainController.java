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
    private enum StateOperation{ StateNone, StatePlus, StateMinus, StateMul, StateDel, StatePow, StateSqrt }
    private StateOperation stateOperation;
    private boolean stateInput = false;
    private boolean dot = false;
    private double firstNumber = 0;
    {
        stateOperation = StateOperation.StateNone;
    }

    @FXML
    public void btnClick(ActionEvent event){
        Button btn = (Button) event.getSource();

        String btnType = btn.getText();
        switch (btnType){
            case "+" -> {inputOperation(StateOperation.StatePlus);}
            case "-" -> {
                if (!stateInput){
                    lbResult.setText("-");
                    stateInput = true;
                }
                else inputOperation(StateOperation.StateMinus);
            }
            case "*" -> {inputOperation(StateOperation.StateMul);}
            case "/" -> {inputOperation(StateOperation.StateDel);}
            case "pow" -> {inputOperation(StateOperation.StatePow);}
            case "sqrt" -> {inputOperation(StateOperation.StateSqrt);}
            case "CE" -> {
                stateOperation = StateOperation.StateNone;
                stateInput = false;
                dot = false;
                firstNumber = 0;
                lbResult.setText("0");
            }
            case "=" -> {
                String number = lbResult.getText();
                if (isNumber(number))
                    lbResult.setText(calcValue(number));
                else {
                    lbResult.setText("Error");
                }
                stateOperation = StateOperation.StateNone;
                stateInput = false;
                dot = false;
            }
            case "." -> {
                if (!stateInput) {
                    lbResult.setText("0.");
                    stateInput = true;
                    dot = true;
                }
                else if (!dot){
                    lbResult.setText(lbResult.getText() + ".");
                    dot = true;
                }
            }
            default -> {
                if (btnType.length() == 1 && Character.isDigit(btnType.charAt(0)))
                    inputNumber(btnType);
                else
                    lbResult.setText("Error");
            }
        }
    }

    @FXML
    public void keyPressed (javafx.scene.input.KeyEvent event){
        if (new KeyCodeCombination(KeyCode.EQUALS, KeyCombination.SHIFT_DOWN).match(event)){
            inputOperation(StateOperation.StatePlus);
        }else if (new KeyCodeCombination(KeyCode.DIGIT8, KeyCombination.SHIFT_DOWN).match(event)) {
            inputOperation(StateOperation.StateMul);
        }
        else{
            String key = event.getText();
            if (key.length() == 1){
                switch (key.charAt(0)){
                    case ('=') -> {
                        String number = lbResult.getText();
                        if (isNumber(number))
                            lbResult.setText(calcValue(number));
                        else {
                            lbResult.setText("Error");
                        }
                        stateOperation = StateOperation.StateNone;
                        stateInput = false;
                        dot = false;
                    }
                    case ('+') -> {inputOperation(StateOperation.StatePlus);}
                    case ('*') -> {inputOperation(StateOperation.StateMul);}
                    case ('/') -> {inputOperation(StateOperation.StateDel);}
                    case ('.') -> {
                        if (!stateInput) {
                            lbResult.setText("0.");
                            stateInput = true;
                            dot = true;
                        }
                        else if (!dot){
                            lbResult.setText(lbResult.getText() + ".");
                            dot = true;
                        }
                    }
                    case (',') -> {
                        if (!stateInput) {
                            lbResult.setText("0.");
                            stateInput = true;
                            dot = true;
                        }
                        else if (!dot){
                            lbResult.setText(lbResult.getText() + ",");
                            dot = true;
                        }
                    }
                    case ('-') -> {
                        if (!stateInput){
                            lbResult.setText("-");
                            stateInput = true;
                        }
                        else inputOperation(StateOperation.StateMinus);
                    }
                    default -> {if(Character.isDigit(key.charAt(0))) inputNumber(key);}
                }
            }
        }
    }

    private void inputNumber (String digit){
        if (!stateInput) {
            lbResult.setText(digit);
            stateInput = true;
        }
        else lbResult.setText(lbResult.getText() + digit);
    }

    private void inputOperation (StateOperation operation){
        String number = lbResult.getText();

        if (isNumber(number)) {
            if (stateOperation != StateOperation.StateNone) {
                lbResult.setText(calcValue(number));
                stateOperation = operation;
                if (isNumber(lbResult.getText()))
                    firstNumber = getNumber(calcValue(number));
                else
                    firstNumber = 0;
            }
            else if (operation == StateOperation.StateSqrt){
                stateOperation = operation;
                lbResult.setText(calcValue(number));
                stateOperation = StateOperation.StateNone;
                if (isNumber(lbResult.getText()))
                    firstNumber = getNumber(calcValue(number));
                else
                    firstNumber = 0;
            }
            //stateOperation = operation;

            //if (isNumber(lbResult.getText()))
            //    firstNumber = getNumber(number);
            //else
            //    firstNumber = 0;


        }
        else lbResult.setText("Error");
        stateInput = false;
        dot = false;
    }

    private String calcValue(String number){
        double secondNumber = getNumber(number);
        switch (stateOperation){
            case StatePlus -> {return String.valueOf(firstNumber + secondNumber);}
            case StateMinus -> {return String.valueOf(firstNumber - secondNumber);}
            case StateMul -> {return String.valueOf(firstNumber * secondNumber);}
            case StateDel -> {
                if (secondNumber != 0.0)
                    return String.valueOf(firstNumber / secondNumber);
                return "Деление на 0";
            }
            case StatePow -> {
                System.out.println(firstNumber);
                System.out.println(secondNumber);
                String result = String.valueOf(Math.pow(firstNumber, secondNumber));
                if (isNumber(result))
                    return result;
                return "Ошибка при возведении в степень";
                }
            case StateSqrt -> {
                if (secondNumber > 0)
                    return String.valueOf(Math.sqrt(secondNumber));
                return "Корень из отрицательного числа";
            }
            case StateNone -> {return String.valueOf(secondNumber);}
        }
        return "Error";
    }

    private double getNumber (String number){
        number = number.replace(',', '.');
        return Double.parseDouble(number);
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == ',' || (str.charAt(i) == '-' && str.length() > 1) || str.charAt(i) == 'E')) return false;
        }
        return true;
    }
}