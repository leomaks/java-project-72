@import gg.jte.Content
@import hexlet.code.dto.BasePage
@param Content content
@param BasePage page = null

<!doctype html>
<html lang="ru">
<head>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page Analyzer</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
          crossorigin="anonymous">

</head>

<header class="p-3 bg-dark text-white">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Анализатор страниц</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <div class="navbar-nav">
                    <a class="nav-link" href="/">Главная</a>
                    <a class="nav-link" href="/urls">Сайты</a>
                </div>
            </div>
        </div>
    </nav>
</header>
<body>
@if(page != null && page.getFlash() != null)
    <p>@if( page.getFlash().contains("уже") || page.getFlash().contains("Некорректный") )
        <div class="alert alert-danger" role="alert">
            ${page.getFlash()}
        </div>
    @else
        <div class="alert alert-success" role="alert">
            ${page.getFlash()}
        </div>
    @endif </p>
@endif

${content}
</body>


<footer class="py-3 my-4">
    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="https://github.com/leomaks" class="nav-link px-2 text-muted">My GitHub</a></li>
    </ul>
</footer>

</html>
