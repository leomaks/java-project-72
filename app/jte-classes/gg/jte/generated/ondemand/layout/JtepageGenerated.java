package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
import hexlet.code.dto.BasePage;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,2,2,37,37,37,39,39,41,41,41,43,43,45,45,45,47,47,50,50,52,52,52,63,63,63,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, BasePage page) {
		jteOutput.writeContent("\n<!doctype html>\n<html lang=\"ru\">\n<head>\n    <meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>Page Analyzer</title>\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"\n          rel=\"stylesheet\"\n          integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"\n          crossorigin=\"anonymous\">\n\n</head>\n\n<header class=\"p-3 bg-dark text-white\">\n    <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n        <div class=\"container-fluid\">\n            <a class=\"navbar-brand\" href=\"/\">Анализатор страниц</a>\n            <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarNav\"\n                    aria-controls=\"navbarNav\" aria-expanded=\"false\" aria-label=\"Toggle navigation\">\n                <span class=\"navbar-toggler-icon\"></span>\n            </button>\n            <div class=\"collapse navbar-collapse\" id=\"navbarNav\">\n                <div class=\"navbar-nav\">\n                    <a class=\"nav-link\" href=\"/\">Главная</a>\n                    <a class=\"nav-link\" href=\"/urls\">Сайты</a>\n                </div>\n            </div>\n        </div>\n    </nav>\n</header>\n<body>\n\n");
		if (page != null && page.getFlash() != null) {
			jteOutput.writeContent("\n    <p>\n    ");
			if ( page.getFlash().contains("уже") || page.getFlash().contains("Некорректный") ) {
				jteOutput.writeContent("\n        <div class=\"alert alert-danger\" role=\"alert\">\n            ");
				jteOutput.setContext("div", null);
				jteOutput.writeUserContent(page.getFlash());
				jteOutput.writeContent("\n        </div>\n    ");
			} else {
				jteOutput.writeContent("\n        <div class=\"alert alert-success\" role=\"alert\">\n            ");
				jteOutput.setContext("div", null);
				jteOutput.writeUserContent(page.getFlash());
				jteOutput.writeContent("\n        </div>\n    ");
			}
			jteOutput.writeContent("\n\n    </p>\n");
		}
		jteOutput.writeContent("\n\n");
		jteOutput.setContext("body", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n</body>\n\n\n<footer class=\"py-3 my-4\">\n    <ul class=\"nav justify-content-center border-bottom pb-3 mb-3\">\n        <li class=\"nav-item\"><a href=\"https://github.com/leomaks\" class=\"nav-link px-2 text-muted\">My GitHub</a></li>\n    </ul>\n</footer>\n\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		BasePage page = (BasePage)params.getOrDefault("page", null);
		render(jteOutput, jteHtmlInterceptor, content, page);
	}
}
