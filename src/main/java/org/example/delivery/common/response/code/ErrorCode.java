package org.example.delivery.common.response.code;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public enum ErrorCode implements ResponseCode {

    // 4xx Client Errors
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "40000000", "잘못된 요청"),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "40100000", "인증되지 않은 요청"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "40300000", "접근 권한 없음"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "40400000", "리소스를 찾을 수 없음"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "40500000", "지원하지 않는 메서드"),
    NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "40600000", "허용되지 않는 응답 형식"),
    CONFLICT(HttpStatus.CONFLICT, "40900000", "리소스 충돌"),
    UNSUPPORTED_MEDIA_TYPE(HttpStatus.UNSUPPORTED_MEDIA_TYPE, "41500000", "지원하지 않는 미디어 포맷"),

    // 5xx Server Errors
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "50000000", "서버 내부 오류"),
    BAD_GATEWAY(HttpStatus.BAD_GATEWAY, "50200000", "외부 서버에서 잘못된 응답 수신"),
    SERVICE_UNAVAILABLE(HttpStatus.SERVICE_UNAVAILABLE, "50300000", "서비스를 일시적으로 사용할 수 없음")
    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String description;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
