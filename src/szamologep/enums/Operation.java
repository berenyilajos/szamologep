package szamologep.enums;

public enum Operation {
    NONE(""), PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");

    private String name;

    private Operation(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static Operation valueByName(String name) {
        for (Operation operation : values()) {
            if (operation.name.equals(name)) {
                return operation;
            }
        }

        throw new RuntimeException("No enum constant szamologep.enums.Operation found with name: " + name);
    }
}
