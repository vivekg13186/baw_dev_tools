package app;

import app.db.model.Connection;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;

public class AppController {
    public static Dao<Connection, String> connectionDAO;
    private static Logger log = LoggerFactory.getLogger(AppController.class);


    public void initDB() throws SQLException {

        String databaseUrl = "jdbc:h2:~/baw_dev.db";
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
        connectionDAO = DaoManager.createDao(connectionSource, Connection.class);
        TableUtils.createTableIfNotExists(connectionSource, Connection.class);
        Unirest.config().verifySsl(false);
    }

    public boolean isEmpty(String s) {
        if (s == null || s.isEmpty()) return true;
        return false;
    }

    public void addConnection(String connectionName, String host, String username, String password) {
        if (isEmpty(connectionName) || isEmpty(host) || isEmpty(username) || isEmpty(password))
            return;
        Connection connection = new Connection();
        connection.setAlias(connectionName);
        connection.setHost(host);
        connection.setUsername(username);
        connection.setPassword(password);
        try {
            connectionDAO.create(connection);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeConnections(List<String> connections) {
        try {
            connectionDAO.deleteIds(connections);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Connection> getAllConnections() {
        try {
            return connectionDAO.queryForAll();
        } catch (SQLException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }

    }


    public HttpResponse<String> getAllApps(String name) throws SQLException, IOException {
        Connection connection = connectionDAO.queryForId(name);
        String path = joinPath(connection.getHost(), "/rest/bpm/wle/v1/processApps");
        log.info("GET "+path);
       return Unirest.get(path).header("Accept","application/json").basicAuth(connection.getUsername(), connection.getPassword()).asString();

    }

    public String joinPath(String base, String path) {
        return URI.create(base).resolve(path).toString();

    }

    public HttpResponse<String> getAllToolkits(String name) throws SQLException, IOException {

        Connection connection = connectionDAO.queryForId(name);
        String path = joinPath(connection.getHost(), "/rest/bpm/wle/v1/toolkit");
        log.info("GET "+path);
        return Unirest.get(path).basicAuth(connection.getUsername(), connection.getPassword()).asString() ;

    }

    public HttpResponse<String> getSnapshotDetails(String con, String snapshotId) throws SQLException, IOException {
        Connection connection = connectionDAO.queryForId(con);
        String path = joinPath(connection.getHost(), "/rest/bpm/wle/v1/processAppSettings");
        log.info("GET "+path);
        return Unirest.get(path)
                .queryString("snapshotId",snapshotId)
                .queryString("recurse","true")
                .basicAuth(connection.getUsername(), connection.getPassword()).asString() ;
    }

    public String getAccessToken(String host, String userName, String password) throws IOException {

        String path = joinPath(host, "/ops/system/login");
        log.info("GET "+path);
        return Unirest.post(path)
                .contentType("application/json")
                .body("{\n" +
                        "  \"refresh_groups\": true,\n" +
                        "  \"requested_lifetime\": 7200\n" +
                        "}")

                .basicAuth(userName, password).asJson().getBody().getObject().get("csrf_token").toString();



    }


    public String getHTTPMethod(String action) {
        switch (action) {
            case "env_vars":

            case "epvs":

            case "team_bindings":

            case "webservice":

            case "rest":
                return "GET";
            case "activate":

            case "archive":

            case "deactivate":

            case "make_default":

            case "update_theme":
                return "POST";
            default:
                throw new RuntimeException("Unknown action id");
        }
    }

    public String getPathForAction(String appId, String snapshotId, String action) {
        switch (action) {
            case "env_vars":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/env_vars", appId, snapshotId);
            case "epvs":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/epvs", appId, snapshotId);
            case "team_bindings":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/team_bindings", appId, snapshotId);
            case "webservice":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/servers/webservice", appId, snapshotId);
            case "rest":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/servers/rest", appId, snapshotId);
            case "activate":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/activate", appId, snapshotId);
            case "archive":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/archive", appId, snapshotId);
            case "deactivate":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/deactivate", appId, snapshotId);
            case "make_default":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/make_default", appId, snapshotId);
            case "update_theme":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/update_theme", appId, snapshotId);
            default:
                throw new RuntimeException("Unknown action id");
        }
    }

    public HttpResponse<String> containerActions(String con, String appId, String snapshotId, String action) throws Exception {
        Connection connection = connectionDAO.queryForId(con);
        String token = getAccessToken(connection.getHost(), connection.getUsername(), connection.getPassword());
        String path = getPathForAction(appId, snapshotId, action);
        path = joinPath(connection.getHost(), path);
        String method = getHTTPMethod(action);

        if(method.equals("GET")) {
            log.info("GET "+path);
            return Unirest.get(path).header("Accept", "application/json").basicAuth(connection.getUsername(), connection.getPassword())
                    .header("BPMCSRFToken", token)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0").asString();

        }else {
            log.info("POST "+path);
            return Unirest.post(path).header("Accept", "application/json").basicAuth(connection.getUsername(), connection.getPassword())
                    .header("BPMCSRFToken", token)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0").asString();

        }

    }

    public  HttpResponse<String>  getInstanceDetails(String host,String username,String password,String instanceId) throws IOException {

        String path   = joinPath(host,String.format("/rest/bpm/wle/v1/process/%s",instanceId.trim()));
        return Unirest.get(path)
                .queryString("parts","all")
                .basicAuth(username,password)
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0")
                .asString() ;

    }

}
