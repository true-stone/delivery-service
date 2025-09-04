package org.example.delivery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "공통", description = "공통 관련 API")
public class CommonController {

    @Operation(summary = "헬스체크 API", description = "서비스에 상태를 확인합니다.")
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
