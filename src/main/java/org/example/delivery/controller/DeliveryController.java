package org.example.delivery.controller;

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
public class DeliveryController {

    private final DeliveryService deliveryService;

    /**
     * 배달 목록 조회
     */
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
    @PatchMapping("/{orderId}/destination")
    public ResponseEntity<ApiResponse<Void>> changeDestination(
        @PathVariable String orderId,
        @RequestBody ChangeDestAddressRequest request) {
        deliveryService.changeDestination(orderId, request);
        return ApiResponse.ok();
    }
}
