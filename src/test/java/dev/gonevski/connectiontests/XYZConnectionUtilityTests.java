package dev.gonevski.connectiontests;

import dev.gonevski.utilities.XYZConnectionUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.sql.Connection;

public class XYZConnectionUtilityTests {
    // Checking our connection to the XYZ Bank Database
    @Test
    void can_connect(){
        Connection conn = XYZConnectionUtility.createConnection();
        Assertions.assertNotNull(conn);
    }
}
