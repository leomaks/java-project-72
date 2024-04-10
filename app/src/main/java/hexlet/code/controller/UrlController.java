package hexlet.code.controller;

import hexlet.code.dto.BasePage;
import hexlet.code.dto.UrlPage;
import hexlet.code.dto.UrlsPage;
import hexlet.code.model.Url;
import hexlet.code.repositories.UrlRepositories;
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
        urls = UrlRepositories.getEntities();
        var page = new UrlsPage(urls);
        page.setFlash(ctx.consumeSessionAttribute("flash"));
        ctx.render("urls.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParam("id");
        var url = UrlRepositories.find(Long.valueOf(id)).get();
        var page = new UrlPage(url);
        ctx.render("url.jte", Collections.singletonMap("page", page));
    }

    public static void add(Context ctx) throws SQLException {
        try {
            var name = ctx.formParamAsClass("url", String.class).get();

            URL parsedURL;
            try {
                parsedURL = new URI(name).toURL();
            } catch (Exception e) {
                ctx.sessionAttribute("flash", "Некорректный URL");
                ctx.redirect(NamedRoutes.homePath());
                return;
            }
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
            ctx.redirect(NamedRoutes.urlsPath());

        } catch (Exception e) {

            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.redirect(NamedRoutes.homePath());

        }

    }


  /*
    public static void build(Context ctx) {

        var page = new BuildCoursePage();
        ctx.render("courses/build.jte", Collections.singletonMap("page", page));
    }

    public static void show(Context ctx) throws SQLException {
        var id = ctx.pathParam("id");
        var course = CoursesRepository.find(Long.valueOf(id)).get();
        var page = new CoursePage(course);
        ctx.render("courses/index.jte", Collections.singletonMap("page", page));
    }
    public  static void create(Context ctx) throws SQLException {
        try {

            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() > 2, "У названия недостаточная длина")
                    .get();
            var description = ctx.formParamAsClass("description", String.class)
                    .check(value -> value.length() > 10, "У описания недостаточная длина")
                    .get();


            var course = new Course(name, description);
            CoursesRepository.save(course);
            ctx.sessionAttribute("flash", "Course has been created!");
            ctx.redirect(NamedRoutes.coursesPath());

        } catch (ValidationException e) {
            var page = new BuildCoursePage(e.getErrors());
            ctx.sessionAttribute("flash", "Error, the item was not created");
            page.setFlash(ctx.consumeSessionAttribute("flash"));
            ctx.render("courses/build.jte", Collections.singletonMap("page", page));
        }
    }

   */


}

