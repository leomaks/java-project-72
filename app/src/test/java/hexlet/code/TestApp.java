package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repositories.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestApp {
    Javalin app;

    @BeforeEach
    public final void setUp() throws SQLException, IOException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.homePath());
            assertThat(response.code()).isEqualTo(200);
            assertTrue((response.body()).string().contains("Бесплатно проверяйте сайты на SEO пригодность"));
        });
    }


    @Test
    public void testUrlsPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
            assertTrue((response.body()).string().contains("Список сайтов"));
        });
    }

    @Test
    public void testUrlPage() throws SQLException {
        String name = "https://www.example.com";
        var url = new Url(name);
        UrlsRepository.save(url);

        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlPath(UrlsRepository.findIdByName(name)));
            assertThat(response.code()).isEqualTo(200);
            assertTrue((response.body()).string().contains(name));
        });
    }


    @Test
    public void testAddUrl() throws SQLException {
        JavalinTest.test(app, (server, client) -> {

            var url = "https://www.test.com";
            var requestBody = "url=" + url;
            var response = client.post(NamedRoutes.urlsPath(), requestBody);

            assertThat(response.code()).isEqualTo(200);
            assertTrue((response.body()).string().contains(url));
            assertThat(UrlsRepository.findByName(url)).isTrue();
        });
    }


    @Test
    public void testAddWrongUrl() throws SQLException {

        JavalinTest.test(app, (server, client) -> {

            var url = "www.WrongURL";
            var requestBody = "url=" + url;
            var response = client.post(NamedRoutes.urlsPath(), requestBody);

            assertThat(response.code()).isEqualTo(200);
            assertFalse((response.body()).string().contains(url));

        });
    }


    @Test
    public void testExistedUrl() throws SQLException {

        JavalinTest.test(app, (server, client) -> {

            var url = "https://www.test.com";
            var url2 = "https://www.test.com/400";

            var requestBody = "url=" + url;
            client.post(NamedRoutes.urlsPath(), requestBody);

            var requestBody2 = "url=" + url2;
            var response = client.post(NamedRoutes.urlsPath(), requestBody2);

            var str = response.body().string();

            assertTrue(str.contains(url));
            assertFalse(str.contains(url2));

            assertThat(UrlsRepository.findByName(url)).isTrue();
            assertThat(UrlsRepository.findByName(url2)).isFalse();

        });
    }
}
