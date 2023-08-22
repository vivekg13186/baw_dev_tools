package baw.tools.controllers;


import baw.tools.entities.Connection;
import baw.tools.repositories.ConnectionRepo;
import kong.unirest.*;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/baw")
public class BAW_RESTController {

    private final ConnectionRepo repository;

    public BAW_RESTController(ConnectionRepo repository) {
        this.repository = repository;
        Unirest.config().verifySsl(false);
        Unirest.config().interceptor(new Interceptor() {
            @Override
            public void onRequest(HttpRequest<?> request, Config config) {
                Interceptor.super.onRequest(request, config);

            }

            @Override
            public void onResponse(HttpResponse<?> response, HttpRequestSummary request, Config config) {
                Interceptor.super.onResponse(response, request, config);
                System.out.println(request.getHttpMethod()+" : "+request.getUrl());
                System.out.println(response.getStatus()+"  : "+response.getStatusText());
            }

            @Override
            public HttpResponse<?> onFail(Exception e, HttpRequestSummary request, Config config) throws UnirestException {
                System.out.println(request.getHttpMethod()+" : "+request.getUrl());
                System.out.println("Fail : "+e.getMessage());
                return Interceptor.super.onFail(e, request, config);

            }
        });
    }

    private String joinPath(String base, String path) {
        return URI.create(base).resolve(path).toString();

    }

    @GetMapping(value = "/{id}/getApps", produces = "application/json")
    public String getApps(@PathVariable Long id) {
        Connection connection = repository.findById(id).orElse(new Connection());
        String path = joinPath(connection.getHost(), "/rest/bpm/wle/v1/processApps");
        return Unirest.get(path).header("Accept", "application/json").basicAuth(connection.getUsername(), connection.getPassword()).asString().getBody();

    }

    @GetMapping(value = "/{id}/getToolkits", produces = "application/json")
    public String getToolkits(@PathVariable Long id) {
        Connection connection = repository.findById(id).orElse(new Connection());
        String path = joinPath(connection.getHost(), "/rest/bpm/wle/v1/toolkit");
        return Unirest.get(path).header("Accept", "application/json").basicAuth(connection.getUsername(), connection.getPassword()).asString().getBody();

    }

    @GetMapping(value = "/{cid}/getSnapshotDetails", produces = "application/json")
    public String getSnapshotDetails(@PathVariable Long cid, @RequestParam String id) {
        Connection connection = repository.findById(cid).orElse(new Connection());
        String path = joinPath(connection.getHost(), "/rest/bpm/wle/v1/processAppSettings");
        return Unirest.get(path)
                .queryString("snapshotId", id)
                .queryString("recurse", "true")
                .basicAuth(connection.getUsername(), connection.getPassword()).asString().getBody();

    }

    private String getAccessToken(String host, String userName, String password) {

        String path = joinPath(host, "/ops/system/login");

        return Unirest.post(path)
                .contentType("application/json")
                .body("{\n" +
                        "  \"refresh_groups\": true,\n" +
                        "  \"requested_lifetime\": 7200\n" +
                        "}")

                .basicAuth(userName, password).asJson().getBody().getObject().get("csrf_token").toString();


    }

    private String getHTTPMethod(String action) {
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
            case "stop":

            case "make_default":

            case "update_theme":
                return "POST";
            default:
                throw new RuntimeException("Unknown action id");
        }
    }

    private String getPathForAction(String appId, String snapshotId, String action) {
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
            case "stop":
                return String.format("/ops/std/bpm/containers/%s/versions/%s/stop", appId, snapshotId);
            default:
                throw new RuntimeException("Unknown action id");
        }
    }

    @GetMapping(value = "/containerAction", produces = "application/json")
    public String containerActions(@RequestParam Long id, @RequestParam String appId, @RequestParam  String snapshotId, @RequestParam  String action) {
        Connection connection = repository.findById(id).orElse(new Connection());
        String token = getAccessToken(connection.getHost(), connection.getUsername(), connection.getPassword());
        String path = getPathForAction(appId, snapshotId, action);
        path = joinPath(connection.getHost(), path);
        String method = getHTTPMethod(action);

        if (method.equals("GET")) {

            return Unirest.get(path).header("Accept", "application/json").basicAuth(connection.getUsername(), connection.getPassword())
                    .header("BPMCSRFToken", token)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0").asString().getBody();

        } else {

            return Unirest.post(path).header("Accept", "application/json").basicAuth(connection.getUsername(), connection.getPassword())
                    .header("BPMCSRFToken", token)
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0").asString().getBody();

        }

    }


    @PostMapping(value = "/getInstanceDetails", produces = "application/json")
    public String getInstanceDetails(@RequestBody GetInstanceDetailsRequest request) {

        String path   = joinPath(request.getHost(),String.format("/rest/bpm/wle/v1/process/%s",request.getInstanceId().trim()));
        return Unirest.get(path)
                .queryString("parts","all")
                .basicAuth(request.getUsername(),request.getPassword())
                .header("Accept", "application/json")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:109.0) Gecko/20100101 Firefox/116.0")
                .asString() .getBody();

    }

}
