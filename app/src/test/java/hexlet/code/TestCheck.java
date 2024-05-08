package hexlet.code;

import hexlet.code.model.Url;
import hexlet.code.repositories.UrlChecksRepository;
import hexlet.code.repositories.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestCheck {

    Javalin app;
    private static MockWebServer mockServer;

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }
    @BeforeAll
    public static void startWebServer() throws IOException {
        mockServer = new MockWebServer();
        mockServer.start();
    }

    @AfterAll
    public static void stopWebServer() throws IOException {
        mockServer.shutdown();
    }

    @Test
    public void testMain() throws SQLException, IOException, InterruptedException {

        var file = "./src/test/resources/test.html";
        var body = Files.readString(Paths.get(file));

        MockResponse mockResponse = new MockResponse();
        mockResponse.setBody(body);

        mockServer.enqueue(mockResponse);

        var url = mockServer.url("/test").toString();
        var actualUrl = new Url(url);
        UrlsRepository.save(actualUrl);

        JavalinTest.test(app, (server, client) -> {
            var id = UrlsRepository.findIdByName(actualUrl.getName());
            var response = client.post(NamedRoutes.urlCheckPath(id));
            var responseBody = response.body().string();

            assertThat(response.code()).isEqualTo(200);
            assertThat(responseBody)
                    .contains("This is test title")
                    .contains("This is h1 test")
                    .contains("Here is description");

            var urlInBD = UrlsRepository.find(Long.valueOf(id)).get();
            var urlsCheckListInBD = UrlChecksRepository.getCheckedUrls(Long.valueOf(id));
            var urlCheck = urlsCheckListInBD.get(0);

            assertThat(urlInBD.getName()).contains("/test");
            assertThat(urlCheck.getH1()).isEqualTo("This is h1 test");
            assertThat(urlCheck.getDescription()).isEqualTo("Here is description");
            assertThat(urlCheck.getTitle()).isEqualTo("This is test title");


        });

    }

}
