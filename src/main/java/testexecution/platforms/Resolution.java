package testexecution.platforms;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Resolution {
    MOBILE("mobile", "373", "667"),
    DESKTOP("desktop", "1440", "1080"),
    DEFAULT("default", "1920", "1080");

    private final String name;
    private final String width;
    private final String height;

    public static Resolution getLabel(String option) {
        for (Resolution device : Resolution.values()) {
            if (device.getName().equalsIgnoreCase(option)) return device;
        }
        return null;
    }
}
