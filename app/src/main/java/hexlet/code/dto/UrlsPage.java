package hexlet.code.dto;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
@AllArgsConstructor
@Getter
public class UrlsPage extends BasePage {
    public List<Url> urls;
    private HashMap<Long, UrlCheck> lastChecks;
}


