package hexlet.code;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;

import hexlet.code.dto.BasePage;
import hexlet.code.repositories.BaseRepository;
import io.javalin.Javalin;
import io.javalin.rendering.template.JavalinJte;
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
    public static Javalin getApp() throws IOException, SQLException {

        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getBD());

        var dataSource = new HikariDataSource(hikariConfig);
        BaseRepository.dataSource = dataSource;



        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });


      //  app.get("/", ctx -> ctx.result("Hello World"));
//        ctx.render("index.jte", Collections.singletonMap("page", page));

        app.get("/", ctx -> {
            var page = new BasePage();
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("index.jte", Collections.singletonMap("page", page));
        });


        log.info("Hello, World error");


        return app;
    }
    public static void main(String[] args) throws IOException, SQLException {
        var app = getApp();
        app.start(getPort());
    }
}
