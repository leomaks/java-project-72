@import hexlet.code.dto.BasePage
@param BasePage page


@template.layout.page(page = page,
    
    
content = @`


    <form action="/urls" method="post">
        <div>
            <label>
                Название сайта
                <input type="text"  class="form-control rounded" placeholder="Введите название"  aria-describedby="search-addon" required name="name" />
            </label>
        </div>
     
        <input type="submit" class="btn btn-outline-primary" value="Добавить" />
    </form>

`
)


