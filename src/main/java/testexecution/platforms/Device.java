package testexecution.platforms;

import java.util.Arrays;
import java.util.List;

public enum Device implements Platform {

    PIXEL_5("pixel 5", "andriod");

    public final List<String> label;

    Device(String... label) {
        this.label = Arrays.asList(label);
    }

    @Override
    public String getName() {
        return label.get(0);
    }
}
