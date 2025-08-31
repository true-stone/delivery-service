package org.example.delivery.common.response.code;

import org.springframework.http.HttpStatus;

public interface ResponseCode {
    HttpStatus getHttpStatus();
    String getCode();
    String getDescription();
}
