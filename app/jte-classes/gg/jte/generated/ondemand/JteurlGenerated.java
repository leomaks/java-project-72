package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlPage;
import java.time.format.DateTimeFormatter;
public final class JteurlGenerated {
	public static final String JTE_NAME = "url.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,6,6,9,9,11,11,11,17,17,17,23,23,23,27,29,29,29,29,35,35,35,35,45,45,60,60,63,63,63,64,64,64,65,65,65,66,66,66,67,67,67,68,70,70,70,70,73,73,76,76,80,80,80,80,80,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n <div class=\"container-lg mt-5\">\n    <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\n\n    <table class=\"table table-bordered table-hover mt-3\">\n        <tbody>\n        <tr>\n            <th scope=\"col\">ID</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\n\n        </tr>\n\n        <tr>\n            <th scope=\"col\">Имя</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\n        </tr>\n        <tr>\n            <th scope=\"col\">Дата создания</th>\n            <td>");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAt().toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                        .toString());
				jteOutput.writeContent("</td>\n        </tr>\n        </tbody>\n    </table>\n\n     <h2 class=\"mt-5\">Проверки</h2>\n    <form action=\"/urls/");
				jteOutput.setContext("form", "action");
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.setContext("form", null);
				jteOutput.writeContent("/checks\" method=\"post\" class=\"rss-form text-body\">\n        <div class=\"row\">\n            <div class=\"col-auto\">\n                <button type=\"submit\" class=\"h-100 btn btn-lg btn-primary px-sm-5\">Запустить проверку</button>\n            </div>\n        </div>\n    </form>\n\n\n\n    ");
				if (page.getUrlChecks() != null) {
					jteOutput.writeContent("\n    <table class=\"table table-bordered table-hover mt-3\">\n        <thead>\n        <tr>\n            <th scope=\"col\">ID</th>\n            <th scope=\"col\">Код ответа</th>\n            <th scope=\"col\">Title</th>\n            <th scope=\"col\">H1</th>\n            <th scope=\"col\">Description</th>\n            <th scope=\"col\">Дата проверки</th>\n\n        </tr>\n        </thead>\n\n\n        ");
					for (var url : page.getUrlChecks()) {
						jteOutput.writeContent("\n            <tbody>\n            <tr>\n                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getId());
						jteOutput.writeContent("</td>\n                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getStatusCode());
						jteOutput.writeContent("</td>\n                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getTitle());
						jteOutput.writeContent("</td>\n                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getH1());
						jteOutput.writeContent("</td>\n                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getDescription());
						jteOutput.writeContent("</td>\n                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(url.getCreatedAt().toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                        .toString());
						jteOutput.writeContent("</td>\n            </tr>\n            </tbody>\n        ");
					}
					jteOutput.writeContent("\n    </table>\n\n    ");
				}
				jteOutput.writeContent("\n </div>\n\n\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
