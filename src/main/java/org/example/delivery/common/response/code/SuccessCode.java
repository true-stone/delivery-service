package org.example.delivery.common.response.code;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

/**
 * 성공 코드
 * <pre>
 * 응답 코드는 총 8자리
 * 2XX(HTTP_STATUS_CODE), XX(SERVICE_CODE), XXX(CUSTOM_CODE)
 *
 * SERVICE_CODE
 * - 00 : 공통
 * </pre>
 */
@AllArgsConstructor
public enum SuccessCode implements ResponseCode {

    OK(HttpStatus.OK, "20000000", "요청 성공"),
    CREATED(HttpStatus.CREATED, "20100000", "생성 성공"),
    NO_CONTENT(HttpStatus.NO_CONTENT, "20400000", "요청 성공 (컨텐츠 없음)"),
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
