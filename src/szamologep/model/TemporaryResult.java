package szamologep.model;

import szamologep.enums.Operation;

public class TemporaryResult {
    private Operation operation;
    private double number;

    public TemporaryResult(Operation operation, double number) {
        this.operation = operation;
        this.number = number;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }
}
