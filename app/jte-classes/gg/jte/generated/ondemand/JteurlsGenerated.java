package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlsPage;
public final class JteurlsGenerated {
	public static final String JTE_NAME = "urls.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,4,4,7,7,23,23,26,26,26,27,27,27,27,27,27,27,30,30,32,32,32,32,32,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n\n\n<h1>Список сайтов</h1>\n\n    \n    <table class=\"table table-bordered table-hover mt-3\">\n\n        <thead>\n        <tr>\n            <th scope=\"col\">Id</th>\n            <th scope=\"col\">URL</th>\n\n        </tr>\n        </thead>\n\n        ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n            <tbody>\n            <tr>\n                <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</td>\n                <td><a href=\"/urls/");
					jteOutput.setContext("a", "href");
					jteOutput.writeUserContent(url.getId());
					jteOutput.setContext("a", null);
					jteOutput.writeContent("\">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a> </td>\n            </tr>\n            </tbody>\n        ");
				}
				jteOutput.writeContent("\n    </table>\n");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
