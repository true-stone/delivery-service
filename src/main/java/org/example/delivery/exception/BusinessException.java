package org.example.delivery.exception;

import lombok.Getter;
import org.example.delivery.common.response.code.ErrorCode;

/**
 * 모든 비즈니스 예외의 상위 클래스
 */
@Getter
public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
