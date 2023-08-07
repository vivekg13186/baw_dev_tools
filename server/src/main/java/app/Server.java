package app;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import kong.unirest.HttpResponse;
import spark.Spark;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Handler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.*;

public class Server {
    private static Logger log = LoggerFactory.getLogger(Server.class);

    public static void main(String[] arg) throws SQLException {
        port(6023);
        Spark.staticFiles.location("/public");
        AppController controller = new AppController();
        controller.initDB();
        get("api/connections", (req, res) -> {
            Gson gson = new Gson();
            return gson.toJson(controller.getAllConnections());
        });
        post("api/saveConnection", (req, res) -> {
            Gson gson = new Gson();
            System.out.println(req.body());
            JsonObject obj = gson
                    .fromJson(req.body(), JsonElement.class).getAsJsonObject();
            controller.addConnection(obj.get("name").getAsString(), obj.get("host").getAsString(), obj.get("username").getAsString(), obj.get("password").getAsString());
            return "OK";
        });
        get("api/deleteConnection/:id", (req, res) -> {
            String connection = req.params("id");
            ArrayList l = new ArrayList<>();
            l.add(connection);
            controller.removeConnections(l);
            return "OK";
        });
        get("api/getApps/:id", (req, res) -> {
            System.out.println("getApps");
            String connection = req.params("id");
            HttpResponse<String> output= controller.getAllApps(connection);
            log.info("HTTP Status "+output.getStatus());
            res.status(output.getStatus());
            return output.getBody();

        });
        get("api/getToolkits/:id", (req, res) -> {

            String connection = req.params("id");
            HttpResponse<String> output= controller.getAllToolkits(connection);
            log.info("HTTP Status "+output.getStatus());
            res.status(output.getStatus());
            return output.getBody();
        });
        get("api/getSnapshotDetails/:id", (req, res) -> {
            String connection = req.params("id");
            String snapshotId = req.queryParams("snapshotId");
            HttpResponse<String> output=  controller.getSnapshotDetails(connection, snapshotId);
            log.info("HTTP Status "+output.getStatus());
            res.status(output.getStatus());
            return output.getBody();
        });

        get("api/containerAction", (req, res) -> {
            String connection = req.queryParams("connection");
            String appId = req.queryParams("appId");
            String snapshotId = req.queryParams("snapshotId");
            String action = req.queryParams("action");


            HttpResponse<String> output=  controller.containerActions(
                    connection, appId, snapshotId,action);
            log.info("HTTP Status "+output.getStatus());
            res.status(output.getStatus());
            return output.getBody();
        });
        get("api/getInstanceDetails", (req, res) -> {
            String host = req.queryParams("host");
            String username = req.queryParams("username");
            String password = req.queryParams("password");
            String instanceId = req.queryParams("instanceId");


            HttpResponse<String> output=  controller.getInstanceDetails(
                    host, username, password,instanceId);
            log.info("HTTP Status "+output.getStatus());
            res.status(output.getStatus());
            return output.getBody();
        });
    }
}
