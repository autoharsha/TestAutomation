package testexecution.configuration;

public enum ExecutionMode {

    LOCAL("local"),
    REMOTE("remote");

    private final String mode;

    ExecutionMode(String mode) {
        this.mode = mode;
    }
}
