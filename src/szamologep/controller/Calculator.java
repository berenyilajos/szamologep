package szamologep.controller;

import szamologep.enums.Operation;
import szamologep.model.TemporaryResult;

public class Calculator {
    private TemporaryResult temporaryResult;

    public Calculator(TemporaryResult temporaryResult) {
        this.temporaryResult = temporaryResult;
    }


    public double getResult(String displayText, Operation operation) {
        if (!displayText.equals("0")){
            double number = Double.parseDouble(displayText);
            calculateResult(number);
        }
        temporaryResult.setOperation(operation);

        return temporaryResult.getNumber();
    }

    public double getResult(double number) {
        calculateResult(number);
        temporaryResult.setOperation(Operation.NONE);

        return temporaryResult.getNumber();
    }

    private void calculateResult(double number) {
        switch (temporaryResult.getOperation()){
            case NONE: temporaryResult.setNumber(number); break;
            case PLUS: temporaryResult.setNumber(temporaryResult.getNumber() + number); break;
            case MINUS: temporaryResult.setNumber(temporaryResult.getNumber() - number); break;
            case MULTIPLY: temporaryResult.setNumber(temporaryResult.getNumber() * number); break;
            case DIVIDE: temporaryResult.setNumber(temporaryResult.getNumber() / number); break;
            default: break;
        }
    }

    public void clear() {
        temporaryResult.setOperation(Operation.NONE);
        temporaryResult.setNumber(0);
    }

    public double getSqrt(double number) {
        double newValue = Math.sqrt(number);
        temporaryResult.setOperation(Operation.NONE);
        temporaryResult.getOperation().name();

        return getResult(newValue);
    }

    public double getPow2(double number) {
        double newValue = Math.pow(number, 2);
        temporaryResult.setOperation(Operation.NONE);

        return getResult(newValue);
    }
}
