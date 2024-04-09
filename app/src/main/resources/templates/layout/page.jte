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
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">

            <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                <li><a href="/" class="nav-link px-2 text-white">Главная страница</a></li>
                <li><a href="/build" class="nav-link px-2 text-white">Добавить сайт </a></li>
                <li><a href="/check" class="nav-link px-2 text-white">Проверить сайт</a></li>
            </ul>
        </div>
    </div>
</header>
<body>

${content}
</body>


<footer class="py-3 my-4">
    <ul class="nav justify-content-center border-bottom pb-3 mb-3">
        <li class="nav-item"><a href="https://github.com/leomaks" class="nav-link px-2 text-muted">My GitHub</a></li>
    </ul>
</footer>

</html>
