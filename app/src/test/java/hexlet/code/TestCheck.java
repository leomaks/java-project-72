package hexlet.code;

public class TestCheck {
}

/* Создаём инстанс `MockWebServer`. Вызвав на созданном инстансе метод `mockServer.url("/").toString()`
можно получить адрес сайта, который нужно будет использовать в тестах
2. Создаём инстанс `MockResponse`, и устанавливаем нужное тело ответа.
Это и есть та фейковая страница, а точнее её содержимое (html), с которой будет работать наше приложение в тестах
3. Добавляем инстанс MockResponse в очередь к созданному серверу
4. Запускаем сервер
5. После выполнения тестов обязательно нужно остановить сервер. Воспользуйтесь аннотациями `@BeforeAll` и `@AfterAll` в тестах


public void test() throws Exception {
  // Create a MockWebServer. These are lean enough that you can create a new
  // instance for every unit test.
  MockWebServer server = new MockWebServer();

  // Schedule some responses.
  server.enqueue(new MockResponse().setBody("hello, world!"));
  server.enqueue(new MockResponse().setBody("sup, bra?"));
  server.enqueue(new MockResponse().setBody("yo dog"));

  // Start the server.
  server.start();

  // Ask the server for its URL. You'll need this to make HTTP requests.
  HttpUrl baseUrl = server.url("/v1/chat/");

  // Exercise your application code, which should make those HTTP requests.
  // Responses are returned in the same order that they are enqueued.
  Chat chat = new Chat(baseUrl);

  chat.loadMore();
  assertEquals("hello, world!", chat.messages());

  chat.loadMore();
  chat.loadMore();
  assertEquals(""
      + "hello, world!\n"
      + "sup, bra?\n"
      + "yo dog", chat.messages());

  // Optional: confirm that your app made the HTTP requests you were expecting.
  RecordedRequest request1 = server.takeRequest();
  assertEquals("/v1/chat/messages/", request1.getPath());
  assertNotNull(request1.getHeader("Authorization"));

  RecordedRequest request2 = server.takeRequest();
  assertEquals("/v1/chat/messages/2", request2.getPath());

  RecordedRequest request3 = server.takeRequest();
  assertEquals("/v1/chat/messages/3", request3.getPath());

  // Shut down the server. Instances cannot be reused.
  server.shutdown();
}
 */