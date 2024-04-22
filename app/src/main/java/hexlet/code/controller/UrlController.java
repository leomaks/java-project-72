package hexlet.code.controller;

import hexlet.code.dto.BasePage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repositories.UrlChecksRepository;
import hexlet.code.repositories.UrlsRepository;
import hexlet.code.util.NamedRoutes;
import io.javalin.http.Context;
import java.net.URI;
import java.net.URL;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class UrlController {

    public static  void index(Context ctx) throws SQLException {
        var page = new BasePage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static  void urls(Context ctx) throws SQLException {
        List<Url> urls;
        urls = UrlsRepository.getEntities();
        var lastChecks = UrlChecksRepository.findLastChecks();


        var page = new UrlsPage(urls, lastChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParam("id");
        var url = UrlsRepository.find(Long.valueOf(id)).get();
        var page = new UrlPage(url);
        ctx.render("url.jte", Collections.singletonMap("page", page));
    }

    public static void add(Context ctx) throws SQLException {

        var name = ctx.formParamAsClass("url", String.class).get();

        URL parsedUrl;
        try {
            parsedUrl = new URI(name).toURL();
        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.redirect(NamedRoutes.homePath());
            return;
        }

        String normalizedUrl = String
                .format(
                        "%s://%s%s",
                        parsedUrl.getProtocol(),
                        parsedUrl.getHost(),
                        parsedUrl.getPort() == -1 ? "" : ":" + parsedUrl.getPort()
                )
                .toLowerCase();


        if (UrlsRepository.findByName(normalizedUrl)) {

            ctx.sessionAttribute("flash", "Страница уже существует");

        } else {

            var url = new Url(normalizedUrl);
            UrlsRepository.save(url);
            ctx.sessionAttribute("flash", "Страница успешно добавлена");

        }
        ctx.redirect(NamedRoutes.urlsPath());

    }

}

