package org.example.delivery.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

import static io.swagger.v3.oas.annotations.media.Schema.RequiredMode.REQUIRED;

@Getter
@Schema(description = "공통 페이징 응답")
@AllArgsConstructor
public class PagingResponse<T> {

    /**
     * 조회된 컨텐츠 목록
     */
    @Schema(description = "응답 목록 데이터", requiredMode = REQUIRED)
    private final List<T> list;

    /**
     * 전체 컨텐츠 개수
     */
    @Schema(description = "전체 목록 개수", requiredMode = REQUIRED)
    private final long totalCount;

    /**
     * 현재 페이지 번호
     */
    @Schema(description = "현재 페이지 목록", requiredMode = REQUIRED)
    private final int page;

    /**
     * 현재 페이지 컨텐츠 개수
     */
    @Schema(description = "현재 페이지 컨텐츠 개수", requiredMode = REQUIRED)
    private final int size;

    /**
     * 다음 페이지 존재 여부
     */
    @Schema(description = "다음 페이지 존재 여부", requiredMode = REQUIRED)
    private final boolean hasNext;

    public PagingResponse(Page<T> page) {
        this.list = page.getContent();
        this.totalCount = page.getTotalElements();
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
        this.hasNext = page.hasNext();
    }

    public static <T> PagingResponse<T> of(Page<T> page) {
        return new PagingResponse<>(page);
    }
}
