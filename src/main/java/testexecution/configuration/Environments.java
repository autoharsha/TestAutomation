package testexecution.configuration;

public enum Environments {
    QA,
    SAT,
    DEV;

    public String getEnvName() {
        return this.name();
    }
}
