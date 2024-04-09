package hexlet.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import hexlet.code.dto.BasePage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repositories.BaseRepository;
import hexlet.code.repositories.UrlRepositories;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;


@Slf4j
public class App {

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    static String getBD() {
        String bd = System.getenv().getOrDefault("JDBC_DATABASE_URL",
                "jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
        return bd;
    }


    private static String getContent(InputStream is) throws IOException {
        try (var reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }

    private static InputStream getFile(String fileName) {
        var classLoader = App.class.getClassLoader();
        var inputStream = classLoader.getResourceAsStream(fileName);
        return inputStream;
    }


    public static Javalin getApp() throws IOException, SQLException {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getBD());

        var dataSource = new HikariDataSource(hikariConfig);
        var sql = getContent(getFile("schema.sql"));

        log.info(sql);

        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }

        BaseRepository.dataSource = dataSource;


        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });


        app.get("/", ctx -> {
            var page = new BasePage();
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("index.jte", Collections.singletonMap("page", page));
        });

        app.get("/urls", ctx -> {

            List<Url> urls;
            urls = UrlRepositories.getEntities();

            var page = new UrlsPage(urls);
            ctx.render("urls.jte", Collections.singletonMap("page", page));
        });

        app.get("/urls/{id}", ctx -> {
            ///
            var id = ctx.pathParam("id");
            var url = UrlRepositories.find(Long.valueOf(id)).get();
            var page = new UrlPage(url);
            ctx.render("url.jte", Collections.singletonMap("page", page));

        });


        app.post("/urls", ctx -> {
            try {
                var name = ctx.formParamAsClass("name", String.class).get();
                var parsedURL = new URL(name).toURI();
                var host = parsedURL.getHost();
                var port = parsedURL.getPort();
                if (port != -1) {
                    host = host + ":" + port;
                }
                if (UrlRepositories.findByName(host)) {
                    ctx.sessionAttribute("flash", "Страница уже существует");
                } else {
                    var url = new Url(host);
                    UrlRepositories.save(url);
                    ctx.sessionAttribute("flash", "Страница успешно добавлена");
                }

               // var flash = ctx.consumeSessionAttribute("flash")
                // Добавляем flash в определение CoursesPage

                List<Url> urls;
                urls = UrlRepositories.getEntities();
                var page = new UrlsPage(urls);
                page.setFlash(ctx.consumeSessionAttribute("flash"));
                ctx.render("urls.jte", Collections.singletonMap("page", page));

            } catch (ValidationException e) {
                ctx.sessionAttribute("flash", "Error, the item was not created");
            }
        });


        log.info("Hello, World error");

        return app;
    }

    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        app.start(getPort());
    }
}
