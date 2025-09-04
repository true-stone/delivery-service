package org.example.delivery.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.example.delivery.common.response.ApiResponse;
import org.example.delivery.common.response.PagingResponse;
import org.example.delivery.dto.request.ChangeDestAddressRequest;
import org.example.delivery.dto.response.DeliveryOrderResponse;
import org.example.delivery.service.DeliveryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
@Tag(name = "배달", description = "배달 관련 API")
public class DeliveryController {

    private final DeliveryService deliveryService;

    /**
     * 배달 목록 조회
     */
    @Operation(summary = "배달 목록 조회 API", description = "회원이 배달한 정보를 목록으로 조회합니다.")
    @Valid
    @GetMapping
    public ResponseEntity<ApiResponse<PagingResponse<DeliveryOrderResponse>>> getDeliveries(
        @RequestParam(defaultValue = "0", required = false) Integer page,
        @RequestParam(defaultValue = "10", required = false) Integer size,
        @RequestParam @NotNull LocalDate from,
        @RequestParam @NotNull LocalDate to
        ) {
        return ApiResponse.ok(deliveryService.getDeliveries(page, size, from, to));
    }

    /**
     * 배달 주문 수정 (도착지 주소 변경)
     */
    @Operation(summary = "배달 도착지 주소 변경 API", description = "회원의 배달 도착지 주소를 변경합니다.")
    @PatchMapping("/{orderId}/destination")
    public ResponseEntity<ApiResponse<Void>> changeDestination(
        @PathVariable String orderId,
        @RequestBody ChangeDestAddressRequest request) {
        deliveryService.changeDestination(orderId, request);
        return ApiResponse.ok();
    }
}
