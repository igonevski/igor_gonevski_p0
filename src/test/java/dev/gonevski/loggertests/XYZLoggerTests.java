package dev.gonevski.loggertests;

import dev.gonevski.utilities.XYZLogger;
import dev.gonevski.utilities.XYZLogLevel;
import org.junit.jupiter.api.Test;

public class XYZLoggerTests {
    @Test
    void info_log_test() {
        XYZLogger.log("Hello", XYZLogLevel.INFO);
        XYZLogger.log("Wassup", XYZLogLevel.DEBUG);
    }
}