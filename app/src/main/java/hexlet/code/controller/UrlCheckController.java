package hexlet.code.controller;


import hexlet.code.model.UrlCheck;
import hexlet.code.repositories.UrlChecksRepository;
import hexlet.code.repositories.UrlsRepository;
import io.javalin.http.Context;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import kong.unirest.Unirest;

import java.sql.SQLException;


public class UrlCheckController {

    public static  void check(Context ctx) throws SQLException {

        var id = ctx.pathParam("id");
        var url = UrlsRepository.find(Long.valueOf(id)).get();

        try {

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

            ctx.sessionAttribute("flash", "Страница успешно проверена");
            UrlController.show(ctx);

        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
            UrlController.show(ctx);
        }


    }
}


