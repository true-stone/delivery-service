package org.example.delivery.controller;

import org.example.delivery.common.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class CommonController {

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, Object>>> health() {
        Map<String, Object> payload = new LinkedHashMap<>();
        payload.put("status", "UP");
        payload.put("time", Instant.now().toString());
        payload.put("uptimeMs", ManagementFactory.getRuntimeMXBean().getUptime());
        payload.put("host", getHostSafe());

        return ApiResponse.ok(payload);
    }

    private String getHostSafe() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (Exception ignore) {
            return "unknown";
        }
    }
}
