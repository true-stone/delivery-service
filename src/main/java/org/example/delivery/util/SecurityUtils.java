package org.example.delivery.util;

import lombok.extern.slf4j.Slf4j;
import org.example.delivery.common.response.code.ErrorCode;
import org.example.delivery.exception.BusinessException;
import org.example.delivery.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Slf4j
public class SecurityUtils {

    /**
     * 현재 로그인한 사용자의 username 을 리턴하는 메서드
     */
    public static String getCurrentUsername() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            log.info("Security Context 에 인증 정보가 없습니다.");
            throw new BusinessException(ErrorCode.UNAUTHORIZED);
        }

        return authentication.getName();
    }

    /**
     * 현재 로그인한 사용자의 내부 ID 를 리턴하는 메서드
     */
    public static Long getCurrentUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            log.info("Security Context 에 인증 정보가 없습니다.");
            throw new BusinessException(ErrorCode.UNAUTHORIZED, "인증정보가 없습니다.");
        }

        if (authentication.getPrincipal() instanceof UserDetailsImpl userDetails) {
            return userDetails.getId();
        }
        throw new BusinessException(ErrorCode.UNAUTHORIZED, "지원하지 않는 인증 주체입니다.");
    }

}
