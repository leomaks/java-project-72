package hexlet.code.model;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@AllArgsConstructor
@Getter
public class UrlCheck {
    private long id;
    private int statusCode;
    private String title;
    private String h1;
    private String description;
    private long urlId;
    private Timestamp createdAt;

    public UrlCheck(long urlId, int statusCode, Timestamp createdAt) {
        this.urlId = urlId;
        this.statusCode = statusCode;
        this.createdAt = createdAt;
    }

    public UrlCheck(int statusCode, String title, String h1, String description, long urlId) {
        this.statusCode = statusCode;
        this.title = title;
        this.h1 = h1;
        this.description = description;
        this.urlId = urlId;
    }
}

