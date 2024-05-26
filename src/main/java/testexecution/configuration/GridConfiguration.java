package testexecution.configuration;

public enum GridConfiguration {

    LOCAL(false, "No Grid required"),
    LOCAL_GRID(true, "http://localhost:4444/wd/hub");


    private final String hubUrl;
    private final boolean gridEnabled;

    GridConfiguration(boolean grid, String hubUrl) {
        this.hubUrl = hubUrl;
        this.gridEnabled = grid;
    }

    public String getHubUrl() {
        return this.hubUrl;
    }

    public boolean isGridEnabled() {
        return this.gridEnabled;
    }


    public String getName() {
        return this.name();
    }

}
