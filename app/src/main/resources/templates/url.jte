@import hexlet.code.dto.UrlPage
@import java.time.format.DateTimeFormatter

@param UrlPage page


@template.layout.page(page = page,

content = @`
 <div class="container-lg mt-5">
    <h1>Сайт: ${page.getUrl().getName()}</h1>

    <table class="table table-bordered table-hover mt-3">
        <tbody>
        <tr>
            <th scope="col">ID</th>
            <td>${page.getUrl().getId()}</td>

        </tr>

        <tr>
            <th scope="col">Имя</th>
            <td>${page.getUrl().getName()}</td>
        </tr>
        <tr>
            <th scope="col">Дата создания</th>
            <td>${page.getUrl().getCreatedAt().toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                        .toString()}</td>
        </tr>
        </tbody>
    </table>

     <h2 class="mt-5">Проверки</h2>
    <form action="/urls/${page.getUrl().getId()}/checks" method="post" class="rss-form text-body">
        <div class="row">
            <div class="col-auto">
                <button type="submit" class="h-100 btn btn-lg btn-primary px-sm-5">Запустить проверку</button>
            </div>
        </div>
    </form>



    @if(page.getUrlChecks() != null)
    <table class="table table-bordered table-hover mt-3">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Код ответа</th>
            <th scope="col">Title</th>
            <th scope="col">H1</th>
            <th scope="col">Description</th>
            <th scope="col">Дата проверки</th>

        </tr>
        </thead>


        @for(var url : page.getUrlChecks())
            <tbody>
            <tr>
                <td>${url.getId()}</td>
                <td>${url.getStatusCode()}</td>
                <td>${url.getTitle()}</td>
                <td>${url.getH1()}</td>
                <td>${url.getDescription()}</td>
                <td>${url.getCreatedAt().toLocalDateTime()
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                        .toString()}</td>
            </tr>
            </tbody>
        @endfor
    </table>

    @endif
 </div>


`
)