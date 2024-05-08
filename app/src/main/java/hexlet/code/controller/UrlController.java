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

public class UrlController {

    public static  void index(Context ctx) throws SQLException {
        var page = new BasePage();
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("index.jte", Collections.singletonMap("page", page));
    }

    public static  void urls(Context ctx) throws SQLException {

        var urls = UrlsRepository.getEntities();
        var lastChecks = UrlChecksRepository.findLastChecks();

        var page = new UrlsPage(urls, lastChecks);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {

        var id = ctx.pathParam("id");
        var url = UrlsRepository.find(Long.valueOf(id)).get();
        var urlChecks = UrlChecksRepository.getCheckedUrls(url.getId());
        String k = ctx.consumeSessionAttribute("flash");
        var page = new UrlPage(url, urlChecks);
        page.setFlash(k);
        ctx.render("url.jte", Collections.singletonMap("page", page));

    }

    public static String normalizeURL(String name)  throws Exception {

        URL parsedUrl;
        try {
            parsedUrl = new URI(name).toURL();
            String normalizedUrl = String
                    .format(
                            "%s://%s%s",
                            parsedUrl.getProtocol(),
                            parsedUrl.getHost(),
                            parsedUrl.getPort() == -1 ? "" : ":" + parsedUrl.getPort()
                    )
                    .toLowerCase();

            return normalizedUrl;

        } catch (Exception e) {
            throw new Exception();
        }

    }

    public static void add(Context ctx) throws Exception {

        var name = ctx.formParamAsClass("url", String.class).get();

        try {
            var normalizedUrl = normalizeURL(name);

            if (UrlsRepository.findByName(normalizedUrl)) {
                ctx.sessionAttribute("flash", "Страница уже существует");
            } else {
                var url = new Url(normalizedUrl);
                UrlsRepository.save(url);
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
            }

            ctx.redirect(NamedRoutes.urlsPath());


        } catch (Exception e) {

            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.redirect(NamedRoutes.homePath());

        }
    }
}


