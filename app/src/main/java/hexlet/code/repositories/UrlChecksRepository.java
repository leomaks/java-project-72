package hexlet.code.repositories;

import hexlet.code.model.UrlCheck;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class UrlChecksRepository extends BaseRepository {
    public static void save(UrlCheck url) throws SQLException {
        String sql = "INSERT INTO url_checks (status_code, title, h1, description, url_id, created_at) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (var conn = dataSource.getConnection();
             var preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, url.getStatusCode());
            preparedStatement.setString(2, url.getTitle());
            preparedStatement.setString(3, url.getH1());
            preparedStatement.setString(4, url.getDescription());
            preparedStatement.setLong(5, url.getUrlId());
            var createdAt = new Timestamp(new Date().getTime());
            preparedStatement.setTimestamp(6, createdAt);
            preparedStatement.executeUpdate();
        }
    }


    public static List<UrlCheck> getCheckedUrls(Long urlId) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE url_id = ?";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, urlId);
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<UrlCheck>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var statusCode = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h1");
                var description = resultSet.getString("description");
                var createdAt = resultSet.getTimestamp("created_at");

                var urlChecked = new UrlCheck(id, statusCode, title, h1, description, urlId, createdAt);
                result.add(urlChecked);
            }
            return result;
        }
    }

   /* public static  Optional<UrlCheck> findLastCheck(Long id) throws SQLException {
        var sql = "SELECT * FROM url_checks WHERE id = ?" +
                "ORDER BY created_at DESC LIMIT 1";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var status_code = resultSet.getInt("status_code");
                var createdAt = resultSet.getTimestamp("created_at");
                var urlLastCheck = new UrlCheck(url_id, status_code, createdAt);
                return Optional.of(urlLastCheck);
            }
            return Optional.empty();
        }
    }

    */

    public static HashMap<Long, UrlCheck> findLastChecks() throws SQLException {

        var urlLastChecks = new HashMap<Long, UrlCheck>();
        var sql = "SELECT DISTINCT ON (url_id) url_id, id, status_code, created_at "
                + "FROM url_checks "
                + "ORDER BY url_id, created_at DESC";
        try (var conn = dataSource.getConnection();
             var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                var urlId = resultSet.getLong("url_id");
                var statusCode = resultSet.getInt("status_code");
                var createdAt = resultSet.getTimestamp("created_at");
                var urlLastCheck = new UrlCheck(urlId, statusCode, createdAt);
                urlLastChecks.put(urlId, urlLastCheck);
            }
        }

        return urlLastChecks;
    }
}




