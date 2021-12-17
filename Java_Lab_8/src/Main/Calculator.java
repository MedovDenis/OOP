package Main;

public class Calculator {
    private enum StateOperation{ StateNone, StatePlus, StateMinus, StateMul, StateDel, StatePow, StateSqrt }
    private static StateOperation stateOperation = StateOperation.StateNone;
    private static boolean stateInput = false;
    private static boolean dot = false;
    private static double firstNumber = 0;

    public static String calculated (String btnValue, String number){
        String result;

        switch (btnValue){
            case "+" -> result = inputOperation(StateOperation.StatePlus, number);
            case "-" -> {
                if (!stateInput){
                    stateInput = true;
                    result = "-";
                }
                else result = inputOperation(StateOperation.StateMinus, number);
            }
            case "*" -> result = inputOperation(StateOperation.StateMul, number);

            case "/" -> result = inputOperation(StateOperation.StateDel, number);

            case "pow" -> result = inputOperation(StateOperation.StatePow, number);
            case "sqrt" -> result = inputOperation(StateOperation.StateSqrt, number);
            case "CE" -> {
                stateOperation = StateOperation.StateNone;
                stateInput = false;
                dot = false;
                firstNumber = 0;
                result = "0";
            }
            case "=" -> {
                if (isNumber(number))
                    result = calcValue(number);
                else {
                    result = "Error";
                }
                stateOperation = StateOperation.StateNone;
                stateInput = false;
                dot = false;
            }
            case "." -> {
                if (!stateInput) {
                    stateInput = true;
                    dot = true;
                    result = "0.";
                }
                else if (!dot){
                    dot = true;
                    result = number + '.';
                }
                else result = number;
            }
            default -> {
                if (btnValue.length() == 1 && Character.isDigit(btnValue.charAt(0)))
                    result = inputNumber(btnValue, number);
                else
                    result = "Error";
            }
        }

        return result;
    }

    private static String inputNumber (String digit, String number){
        if (!stateInput) {
            stateInput = true;
            return digit;
        }
        else return number + digit;
    }

    private static String inputOperation (StateOperation operation, String number){
        String result;
        if (isNumber(number)) {
            if (stateOperation != StateOperation.StateNone) {
                result = calcValue(number);

                if (isNumber(result)){
                    firstNumber = getNumber(calcValue(number));
                    stateOperation = operation;
                }
                else{
                    firstNumber = 0;
                    stateOperation = StateOperation.StateNone;
                }
            }
            else if (operation == StateOperation.StateSqrt){
                stateOperation = operation;
                result = calcValue(number);
                stateOperation = StateOperation.StateNone;

                if (isNumber(result))
                    firstNumber = getNumber(calcValue(number));
                else
                    firstNumber = 0;
            }
            else{
                stateOperation = operation;
                firstNumber = getNumber(number);
                result = number;
            }
        }
        else {
            stateOperation = StateOperation.StateNone;
            result = "Error";
        }

        stateInput = false;
        dot = false;
        return result;
    }

    private static String calcValue(String number){
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

    private static double getNumber (String number){
        number = number.replace(',', '.');
        return Double.parseDouble(number);
    }

    private static boolean isNumber(String str) {
        if (str == null || str.isEmpty()) return false;
        for (int i = 0; i < str.length(); i++) {
            if (!(Character.isDigit(str.charAt(i)) || str.charAt(i) == '.' || str.charAt(i) == ',' || (str.charAt(i) == '-' && str.length() > 1) || str.charAt(i) == 'E')) return false;
        }
        return true;
    }
}
