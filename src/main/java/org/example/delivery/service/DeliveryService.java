package org.example.delivery.service;

import lombok.RequiredArgsConstructor;
import org.example.delivery.common.response.PagingResponse;
import org.example.delivery.common.response.code.ErrorCode;
import org.example.delivery.dto.response.DeliveryOrderResponse;
import org.example.delivery.entity.User;
import org.example.delivery.exception.BusinessException;
import org.example.delivery.repository.DeliveryOrderRepository;
import org.example.delivery.repository.UserRepository;
import org.example.delivery.util.SecurityUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final UserRepository userRepository;
    private final DeliveryOrderRepository deliveryOrderRepository;

    @Transactional(readOnly = true)
    public PagingResponse<DeliveryOrderResponse> getDeliveries(
        int page, int size, LocalDate from, LocalDate to
    ) {

        // 1) 필수 파라미터 검증
        if (from == null || to == null) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "from, to는 필수입니다.");
        }
        if (from.isAfter(to)) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "from이 to보다 미래일 수 없습니다.");
        }

        // 2) 조회 기간 최대 3일 제한
        long daysInclusive = ChronoUnit.DAYS.between(from, to) + 1;
        if (daysInclusive > 3) {
            throw new BusinessException(ErrorCode.BAD_REQUEST, "조회 가능한 기간은 최대 3일입니다.");
        }

        // 3) 기간 경계 계산: [from 00:00:00, to 23:59:59.999999999]
        LocalDateTime fromDt = from.atStartOfDay();
        LocalDateTime toDt = to.plusDays(1).atStartOfDay().minusNanos(1);

        // 4) 로그인 사용자 정보
        String username = SecurityUtils.getCurrentUsername();
        User user = userRepository.findByUsername(username).orElseThrow(()
            -> new UsernameNotFoundException(username));

        // 5) 정렬/페이지
        Pageable pageable = PageRequest.of(Math.max(page - 1, 0), size, Sort.by(Sort.Direction.DESC, "createdAt"));

        // 6) 조회
        var deliveryOrderPage = deliveryOrderRepository.findByUser_IdAndCreatedAtBetweenOrderByCreatedAtDesc(
            user.getId(), fromDt, toDt, pageable
        );

        return PagingResponse.of(deliveryOrderPage.map(DeliveryOrderResponse::from));
    }
}
