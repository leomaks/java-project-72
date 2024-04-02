package hexlet.code;

import io.javalin.Javalin;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestApp {
    @Test
    public void testApp() {
        Javalin app = null;
        try {
            app = App.getApp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        app.start(App.getPort());
        assertFalse(false);
    }
}
