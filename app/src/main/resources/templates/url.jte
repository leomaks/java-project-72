@import hexlet.code.dto.UrlPage
@param UrlPage page


@template.layout.page(
content = @`
    <h1>${page.getUrl().getName()}</h1>
    <p>${page.getUrl().getCreatedAt().toString()}</p>

`
)