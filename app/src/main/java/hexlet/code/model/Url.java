package hexlet.code.model;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Url {
    private long id;
    private String name;
    private Timestamp createdAt;

    public Url(String name) {
        this.name = name;
    }

    public Url(long id, String name, Timestamp createdAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.id = id;
    }
}



