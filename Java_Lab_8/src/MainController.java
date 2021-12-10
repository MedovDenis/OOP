import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class MainController {
    @FXML
    private Button btnEquals;
    @FXML
    private Button btnPlus;
    @FXML
    private Button btnMinus;
    @FXML
    private Button btnMul;
    @FXML
    private Button btnDel;
    @FXML
    private Button btnDot;
    @FXML
    private Button btnCHS;
    @FXML
    private Button btnPow;
    @FXML
    private Button btnSqrt;
    @FXML
    private Button btnCE;
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
    private void btnClickNumber(ActionEvent event) {
        Object obj = event.getSource();
        if (obj instanceof Button btn){
            if (!stateInput) {
                String number = btn.getText();
                tfResult.setText(number);
                stateInput = true;
            }
            else  tfResult.setText(tfResult.getText() + btn.getText());
        }
    }

    @FXML
    private void btnClickCHS(ActionEvent event) {
        String number = tfResult.getText();
        if (number.charAt(0) != '-') tfResult.setText("-" + number);
        else tfResult.setText(number.replace("-", ""));

    }

    @FXML
    private void btnClickDot(ActionEvent event) {
        String result = tfResult.getText() + ".";
        tfResult.setText(result);
    }

    @FXML
    private void btnClickPlus(ActionEvent event) {
        double number = getNumber(tfResult.getText());
        if (number != Double.NaN) {
            if (stateOperation != StateOperation.StateNone) tfResult.setText(String.valueOf(calcValue(number)));
            stateOperation = StateOperation.StatePlus;
        }
        else {
            tfResult.setText("Error");
        }
        oldNumber = Double.parseDouble(tfResult.getText());
        stateInput = false;
    }

    @FXML
    private void btnClickMinus(ActionEvent event) {
        if (stateInput){
            double number = getNumber(tfResult.getText());
            if (number != Double.NaN) {
                if (stateOperation != StateOperation.StateNone) tfResult.setText(String.valueOf(calcValue(number)));
                stateOperation = StateOperation.StateMinus;
            }
            else {
                tfResult.setText("Error");
            }
            oldNumber = Double.parseDouble(tfResult.getText());
            stateInput = false;
        }
        else {
            tfResult.setText("-");
            stateInput = true;
        }

    }

    @FXML
    private void btnClickEquels(ActionEvent event) {
        double number = getNumber(tfResult.getText());
        tfResult.setText(String.valueOf(calcValue(number)));
        stateOperation = StateOperation.StateNone;
        stateInput = false;
    }

    @FXML
    private void btnClickMul(ActionEvent event) {
        double number = getNumber(tfResult.getText());
        if (number != Double.NaN) {
            if (stateOperation != StateOperation.StateNone) tfResult.setText(String.valueOf(calcValue(number)));
            stateOperation = StateOperation.StateMul;
        }
        else {
            tfResult.setText("Error");
        }
        oldNumber = Double.parseDouble(tfResult.getText());
        stateInput = false;
    }

    @FXML
    private void btnClickDel(ActionEvent event) {
        double number = getNumber(tfResult.getText());
        if (number != Double.NaN) {
            if (stateOperation != StateOperation.StateNone) tfResult.setText(String.valueOf(calcValue(number)));
            stateOperation = StateOperation.StateDel;
        }
        else {
            tfResult.setText("Error");
        }
        oldNumber = Double.parseDouble(tfResult.getText());
        stateInput = false;
    }

    @FXML
    private void btnClickPow(ActionEvent event) {
        double number = getNumber(tfResult.getText());
        if (number != Double.NaN) {
            if (stateOperation != StateOperation.StateNone) tfResult.setText(String.valueOf(calcValue(number)));
            stateOperation = StateOperation.StatePow;
        }
        else {
            tfResult.setText("Error");
        }
        oldNumber = Double.parseDouble(tfResult.getText());
        stateInput = false;
    }

    @FXML
    private void btnClickSqrt(ActionEvent event) {
        double number = getNumber(tfResult.getText());
        if (number != Double.NaN && number >= 0){
            if (stateOperation != StateOperation.StateNone) tfResult.setText(String.valueOf(calcValue(number)));
            stateOperation = StateOperation.StateSqrt;
            tfResult.setText(String.valueOf(calcValue(number)));
            stateOperation = StateOperation.StateNone;
        }
        else {
            tfResult.setText("Error");
        }
        oldNumber = Double.parseDouble(tfResult.getText());
        stateInput = false;
    }

    @FXML
    private void btnClickCE(ActionEvent event) {
        stateOperation = StateOperation.StateNone;
        stateInput = false;
        oldNumber = 0;
        tfResult.setText("0");
    }

    private double getNumber (String number){
        double result = Double.NaN;
        if (isNumber(number)){
            boolean dot = false;
            char[] stringBuffer = new char[number.length()];
            int i = 0;
            for(Character symbol : number.toCharArray()){
                if (symbol.equals('.')){
                    if (!dot) {
                        stringBuffer[i] = symbol;
                        dot = true;
                        i ++;
                    }
                }
                else{
                    stringBuffer[i] = (symbol);
                    i++;
                }
            }
            result = Double.parseDouble(new String(stringBuffer));
        }
        return result;
    }

    private boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.')) return false;
        }
        return true;
    }

    private double calcValue(double number){
        double result = Double.NaN;
        switch (stateOperation){
            case StatePlus:
                result =  oldNumber + number;
                break;
            case StateMinus:
                result =  oldNumber - number;
                break;
            case StateMul:
                result =  oldNumber * number;
                break;
            case StateDel:
                if (number != 0) result =  oldNumber / number;
                break;
            case StatePow:
                result =  Math.pow(oldNumber, number);
                break;
            case StateSqrt:
                result =  Math.sqrt(number);
                break;
        }
        return  result;
    }
}