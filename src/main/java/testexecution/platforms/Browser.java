package testexecution.platforms;

public enum Browser implements Platform {
    CHROME("Chrome"),
    SAFARI("Safari"),
    FIREFOX("Firefox"),
    EDGE("Edge");

    public final String label;

    Browser(String label) {
        this.label = label;
    }

    public static Browser getValue(String option) {
        for (Browser value : Browser.values()) {
            if (value.label.equalsIgnoreCase(option)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String getName() {
        return label;
    }
}
