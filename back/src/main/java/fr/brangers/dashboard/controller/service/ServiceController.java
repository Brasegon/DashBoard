package fr.brangers.dashboard.controller.service;

import fr.brangers.dashboard.controller.register.SerializeRegister;
import fr.brangers.dashboard.controller.service.widget.SWidget;
import fr.brangers.dashboard.controller.service.widget.SWidgetDel;
import fr.brangers.dashboard.controller.service.widget.SWidgetUpdate;
import fr.brangers.dashboard.message.DataResponse;
import fr.brangers.dashboard.message.IResponse;
import fr.brangers.dashboard.message.Response;
import fr.brangers.dashboard.service.Data;
import fr.brangers.dashboard.service.register.RegisterService;
import fr.brangers.dashboard.service.service.ServiceList;
import fr.brangers.dashboard.service.service.widget.AddWidgetService;
import fr.brangers.dashboard.service.service.widget.DelWidgetService;
import fr.brangers.dashboard.service.service.widget.GetWidgets;
import fr.brangers.dashboard.service.service.widget.UpdateWidgetService;
import fr.brangers.utils.JwtToken;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
public class ServiceController {
    @GetMapping(value = "/api/service/getList", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getServiceList(@RequestHeader(value = "x-auth-token", defaultValue = "") String token,
                                            @RequestHeader(value = "method", defaultValue = "legacy") String method) {
        JSONObject object = JwtToken.getTokenInformation(token, method);

        if (object == null) {
            return ResponseEntity.status(400).body(new Response("Unauthorized", "error"));
        }
        ServiceList serviceList = new ServiceList(object);
        return ResponseEntity.status(200).body(serviceList.launch());
    }

    @PostMapping(value = "/api/service/addWidget", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> addWidget(@RequestHeader(value = "x-auth-token", defaultValue = "") String token,
                                            @RequestHeader(value = "method", defaultValue = "legacy") String method,
                                       @RequestBody SWidget widget) {
        JSONObject object = JwtToken.getTokenInformation(token, method);

        if (object == null) {
            return ResponseEntity.status(400).body(new Response("Unauthorized", "error"));
        }
        AddWidgetService serviceList = new AddWidgetService(object, widget);
        return ResponseEntity.status(200).body(serviceList.launch());
    }

    @PostMapping(value = "/api/service/removeWidget", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> delWidget(@RequestHeader(value = "x-auth-token", defaultValue = "") String token,
                                       @RequestHeader(value = "method", defaultValue = "legacy") String method,
                                       @RequestBody SWidgetDel widget) {
        JSONObject object = JwtToken.getTokenInformation(token, method);

        if (object == null) {
            return ResponseEntity.status(400).body(new Response("Unauthorized", "error"));
        }
        DelWidgetService serviceList = new DelWidgetService(object, widget);
        return ResponseEntity.status(200).body(serviceList.launch());
    }

    @PostMapping(value = "/api/service/updateWidget", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> updateWidget(@RequestHeader(value = "x-auth-token", defaultValue = "") String token,
                                       @RequestHeader(value = "method", defaultValue = "legacy") String method,
                                       @RequestBody SWidgetUpdate widget) {
        JSONObject object = JwtToken.getTokenInformation(token, method);

        if (object == null) {
            return ResponseEntity.status(400).body(new Response("Unauthorized", "error"));
        }
        UpdateWidgetService serviceList = new UpdateWidgetService(object, widget);
        return ResponseEntity.status(200).body(serviceList.launch());
    }
    @GetMapping(value = "/api/service/getWidgets", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getWidgets(@RequestHeader(value = "x-auth-token", defaultValue = "") String token,
                                          @RequestHeader(value = "method", defaultValue = "legacy") String method) {
        JSONObject object = JwtToken.getTokenInformation(token, method);

        if (object == null) {
            return ResponseEntity.status(400).body(new Response("Unauthorized", "error"));
        }
        GetWidgets serviceList = new GetWidgets(object);
        return ResponseEntity.status(200).body(serviceList.launch());
    }
}
