package hexlet.code.controller;

import hexlet.code.dto.UrlPage;
import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repositories.UrlChecksRepository;
import hexlet.code.repositories.UrlsRepository;
import io.javalin.http.Context;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import kong.unirest.Unirest;
import java.sql.SQLException;
import java.util.Collections;


public class UrlCheckController {

    public static  void showChecks(Url url, Context ctx) throws SQLException {

        var urlChecks = UrlChecksRepository.getCheckedUrls(url.getId());
        var page = new UrlPage(url, urlChecks);
        ctx.sessionAttribute("flash", "Страница успешно проверена");
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("url.jte", Collections.singletonMap("page", page));
    }

    public static  void check(Context ctx) throws SQLException {

        var id = ctx.pathParam("id");
        var url = UrlsRepository.find(Long.valueOf(id)).get();
        var response = Unirest.get(url.getName()).asString();
        int statusCode = response.getStatus();
        Document doc = Jsoup.parse(response.getBody());

        String title = doc.title();
        String h1 = doc.selectFirst("h1") != null
                ? doc.selectFirst("h1").text() : "";
        String description = doc.selectFirst("meta[name=description]") != null
                ? doc.selectFirst("meta[name=description]").attr("content")
                : "";

        var urlChecked = new UrlCheck(statusCode, title, h1, description, Long.valueOf(id));
        UrlChecksRepository.save(urlChecked);
        showChecks(url, ctx);
    }
}


