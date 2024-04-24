package gg.jte.generated.ondemand;
import hexlet.code.dto.UrlsPage;
import hexlet.code.util.NamedRoutes;
import java.time.format.DateTimeFormatter;
public final class JteurlsGenerated {
	public static final String JTE_NAME = "urls.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,3,3,6,6,9,9,29,29,30,30,33,33,33,34,34,34,34,34,34,34,34,34,34,34,34,35,37,37,37,37,40,40,40,44,44,48,48,48,48,48,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\n\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <main class=\"flex-grow-1\">\n        <section>\n            <div class=\"container-lg mt-5\">\n\n                <h1>Список сайтов</h1>\n\n                <table class=\"table table-bordered table-hover mt-3\">\n                    <thead>\n                    <tr>\n                        <th scope=\"col\">Id</th>\n                        <th scope=\"col\">URL</th>\n                        <th scope=\"col\">Последняя проверка</th>\n                        <th scope=\"col\">Код ответа</th>\n                    </tr>\n                    </thead>\n\n\n\n\n                    ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\n                        ");
					var urlCheck = page.getLastChecks().get(url.getId()); 
					jteOutput.writeContent("\n                        <tbody>\n                        <tr>\n                            <td>");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("</td>\n                            <td><a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a> </td>\n                            <td>   ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck == null ? "" : urlCheck.getCreatedAt().toLocalDateTime()
                                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                                                        .toString());
					jteOutput.writeContent("\n                            </td>\n                            <td>\n                                ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(urlCheck == null ? "" : String.valueOf(urlCheck.getStatusCode()));
					jteOutput.writeContent("\n                            </td>\n                        </tr>\n                        </tbody>\n                   ");
				}
				jteOutput.writeContent("\n                </table>\n            </div>\n        </section>\n    </main>");
			}
		}, page);
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
