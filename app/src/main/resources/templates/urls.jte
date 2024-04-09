@import hexlet.code.dto.UrlsPage
@param UrlsPage page


@template.layout.page(page = page,

content = @`




    
    <table class="table table-bordered table-hover mt-3">

        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">URL</th>

        </tr>
        </thead>

        @for(var url : page.getUrls())
            <tbody>
            <tr>
                <td>${url.getId()}</td>
                <td><a href="/urls/${url.getId()}">${url.getName()}</a> </td>
            </tr>
            </tbody>
        @endfor
    </table>
`
)