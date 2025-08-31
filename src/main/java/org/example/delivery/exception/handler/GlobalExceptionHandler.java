package org.example.delivery.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.example.delivery.common.response.ApiResponse;
import org.example.delivery.common.response.code.ErrorCode;
import org.example.delivery.exception.BusinessException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.ParameterValidationResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /* ================
     * Spring 기본 바인딩/유효성 예외 (400)
     * ================ */

    /**
     * 필수 요청 파라미터 누락
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.warn("MissingParam: name={}, type={}", ex.getParameterName(), ex.getParameterType(), ex);
        return ApiResponse.toEntityObject(
            ErrorCode.BAD_REQUEST,
            String.format("필수 파라미터 '%s'가(이) 누락되었습니다.", ex.getParameterName()));
    }

    /**
     * 타입 불일치
     */
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.warn("TypeMismatch: name={}, value={}, requiredType={}",
            ex.getPropertyName(), ex.getValue(), ex.getRequiredType(), ex);

        return ApiResponse.toEntityObject(
            ErrorCode.BAD_REQUEST,
            String.format("파라미터 '%s'의 값 '%s'은(는) 올바른 타입이 아닙니다. 기대 타입: %s.",
                ex.getPropertyName(), ex.getValue(), ex.getRequiredType().getSimpleName())
        );
    }

    /**
     * 메서드 파라미터 검증 실패 (컨트롤러 메서드 파라미터에 대한)
     */
    @Override
    protected ResponseEntity<Object> handleHandlerMethodValidationException(HandlerMethodValidationException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        for (ParameterValidationResult pvr : ex.getParameterValidationResults()) {
            String param = Optional.ofNullable(pvr.getMethodParameter().getParameterName()).orElse("parameter");
            for (MessageSourceResolvable r : pvr.getResolvableErrors()) {
                String msg = Optional.ofNullable(r.getDefaultMessage()).orElse("요청 값이 유효하지 않습니다.");
                errors.add("'" + param + "' 파라미터 오류: " + msg);
            }
        }

        log.warn("HandlerMethodValidationException errors: {}", errors, ex);

        return ApiResponse.toEntityObject(ErrorCode.BAD_REQUEST, errors.get(0));
    }

    /**
     * 유효성 검사 실패 (@RequestBody 바인딩 후)
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        for (FieldError error : fieldErrors) {
            String errorMessage = String.format("'%s' 파라미터 오류: %s", error.getField(), error.getDefaultMessage());
            errors.add(errorMessage);
        }

        log.warn("MethodArgumentNotValidException errors: {}", errors, ex);

        return ApiResponse.toEntityObject(ErrorCode.BAD_REQUEST, errors.get(0));
    }

    /**
     * JSON 파싱 실패
     */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.warn("MessageNotReadable: {}", ex.getMostSpecificCause(), ex);
        return ApiResponse.toEntityObject(ErrorCode.BAD_REQUEST, "요청 본문을 읽을 수 없습니다.");
    }

    /* ================
     * 404
     * ================ */

    /**
     * 요청에 대한 리소스가 존재하지 않음 - Spring 6.1부터 404 처리 방식 변경
     */
    @Override
    protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.warn("NoResourceFound: method={}, path={}", ex.getHttpMethod(), ex.getResourcePath(), ex);

        return ApiResponse.toEntityObject(ErrorCode.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다.");
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.warn("NoHandlerFound: method={}, url={}", ex.getHttpMethod(), ex.getRequestURL(), ex);

        return ApiResponse.toEntityObject(ErrorCode.NOT_FOUND, "요청한 엔드포인트가 존재하지 않습니다.");
    }

    /* ================
     * 405, 415
     * ================ */

    /**
     * 지원하지 않는 HTTP 요청 메서드 사용
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.warn("HttpRequestMethodNotSupported", ex);
        return ApiResponse.toEntityObject(ErrorCode.METHOD_NOT_ALLOWED);
    }

    /**
     * 지원하지 않는 미디어 타입 요청
     */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.warn("HttpMediaTypeNotSupported", ex);
        return ApiResponse.toEntityObject(ErrorCode.UNSUPPORTED_MEDIA_TYPE);
    }

    /* ================
     * 커스텀/기타 예외
     * ================ */

    // 비즈니스 예외 → 우리가 정의한 ErrorCode 반환
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiResponse<Void>> handleBusinessException(BusinessException e) {
        log.warn("BusinessException: {}", e.getMessage(), e);

        return ApiResponse.error(e.getErrorCode());
    }

    // 그 외 모든 예외 → INTERNAL_SERVER_ERROR
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {

        log.error(ex.getMessage(), ex);
        return ApiResponse.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
