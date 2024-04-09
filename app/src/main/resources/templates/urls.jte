@import hexlet.code.dto.UrlsPage
@param UrlsPage page


@template.layout.page(page = page,


content = @`


    <table class="table table-primary table-striped table-bordered table-sm">

        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Course</th>

        </tr>
        </thead>

        @for(var url : page.getUrls())
            <tbody>
            <tr>
                <td>${url.getId()}</td>
                <td><a href="/courses/${url.getId()}">${url.getName()}</a> </td>
            </tr>
            </tbody>
        @endfor
    </table>
`
)