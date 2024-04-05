package hexlet.code.model;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Url {
    public Url(String name, Timestamp createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public Url(long id, String name, Timestamp createdAt) {
        this.name = name;
        this.createdAt = createdAt;
        this.id = id;
    }

    private long id;
    private String name;
    private Timestamp createdAt;
}



