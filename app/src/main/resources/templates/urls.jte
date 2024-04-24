@import hexlet.code.dto.UrlsPage
@import hexlet.code.util.NamedRoutes
@import java.time.format.DateTimeFormatter
@param UrlsPage page


@template.layout.page(page = page,

content = @`
    <main class="flex-grow-1">
        <section>
            <div class="container-lg mt-5">

                <h1>Список сайтов</h1>

                <table class="table table-bordered table-hover mt-3">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">URL</th>
                        <th scope="col">Последняя проверка</th>
                        <th scope="col">Код ответа</th>
                    </tr>
                    </thead>




                    @for(var url : page.getUrls())
                        !{var urlCheck = page.getLastChecks().get(url.getId()); }
                        <tbody>
                        <tr>
                            <td>${url.getId()}</td>
                            <td><a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a> </td>
                            <td>   ${urlCheck == null ? "" : urlCheck.getCreatedAt().toLocalDateTime()
                                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                                                        .toString()}
                            </td>
                            <td>
                                ${urlCheck == null ? "" : String.valueOf(urlCheck.getStatusCode())}
                            </td>
                        </tr>
                        </tbody>
                   @endfor
                </table>
            </div>
        </section>
    </main>`
)