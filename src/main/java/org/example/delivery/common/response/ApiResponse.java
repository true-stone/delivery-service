package org.example.delivery.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.example.delivery.common.response.code.ErrorCode;
import org.example.delivery.common.response.code.ResponseCode;
import org.example.delivery.common.response.code.SuccessCode;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ApiResponse<T> {

    /**
     * 비즈니스 코드 (ex: 20000000, 50000000)
     */
    private String code;

    /**
     * 코드에 대한 정적 설명 (ex: "요청 성공", "서버 오류")
     */
    private String desc;

    /**
     * 상황별 상세 메시지
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * 실제 응답 데이터
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    /* =========================
     * Factory (core)
     * ========================= */
    public static <T> ApiResponse<T> of(ResponseCode rc, T data, String message) {
        return ApiResponse.<T>builder()
            .code(rc.getCode())
            .desc(rc.getDescription())
            .message(message)
            .data(data)
            .build();
    }

    public static <T> ApiResponse<T> of(ResponseCode rc, T data) {
        return of(rc, data, null);
    }

    public static <T> ApiResponse<T> of(ResponseCode rc) {
        return of(rc, null, null);
    }

    /* =========================
     * ResponseEntity helpers
     * ========================= */
    public static <T> ResponseEntity<ApiResponse<T>> toEntity(ResponseCode rc, T data, String message) {
        return ResponseEntity.status(rc.getHttpStatus()).body(of(rc, data, message));
    }

    public static <T> ResponseEntity<ApiResponse<T>> toEntity(ResponseCode rc, T data) {
        return ResponseEntity.status(rc.getHttpStatus()).body(of(rc, data));
    }

    public static <T> ResponseEntity<Object> toEntityObject(ErrorCode code) {
        return ResponseEntity.status(code.getHttpStatus()).<Object>body(of(code));
    }

    public static ResponseEntity<Object> toEntityObject(ErrorCode code, String message) {
        return ResponseEntity.status(code.getHttpStatus()).<Object>body(of(code, null, message));
    }

    /* =========================
     * Success/Error shortcuts
     * (SuccessCode / ErrorCode 사용 시)
     * ========================= */

    // ✅ Success
    public static <T> ResponseEntity<ApiResponse<T>> ok() {
        return toEntity(SuccessCode.OK, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return toEntity(SuccessCode.OK, data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created() {
        return toEntity(SuccessCode.CREATED, null);
    }

    public static ResponseEntity<ApiResponse<Void>> noContent() {
        return toEntity(SuccessCode.NO_CONTENT, null);
    }

    // ❌ Error
    public static ResponseEntity<ApiResponse<Void>> error(ErrorCode ec) {
        return toEntity(ec, null);
    }

    public static ResponseEntity<ApiResponse<Void>> error(ErrorCode ec, String message) {
        return toEntity(ec, null, message);
    }
}
