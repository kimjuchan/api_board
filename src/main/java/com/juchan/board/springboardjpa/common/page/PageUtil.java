package com.juchan.board.springboardjpa.common.page;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PageUtil {
    //페이지 정렬 타입
    public static final String DEFAULT_SORT_TYPE = "id";
    //페이지 넘버
    private static final String DEFAULT_PAGE_NUM = "1";
    //페이지 당 데이터 목록 수
    private static final String DEFAULT_PAGE_SIZE = "10";
    //페이지 목록에 수 ( 1p... 5p , 6p...10p)
    private static int PER_PAGE_SIZE = 5;

    @Schema(description = "페이지 번호(0...N)", defaultValue = DEFAULT_PAGE_NUM, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int page;

    @Schema(description = "페이지 사이즈(1...N)", defaultValue = DEFAULT_PAGE_SIZE, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private int size;

    @Schema(description = "페이지 Sort", defaultValue = DEFAULT_SORT_TYPE, requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String sort_type;

    /**
     * blockLimit : page 개수 설정
     * 현재 사용자가 선택한 페이지 앞 뒤로 3페이지씩만 보여준다.
     * ex : 현재 사용자가 4페이지라면 2, 3, (4), 5, 6
     */
    private int startPage = 1;
    //default : 10 -> 이거 설정 어디서 해야할지 모르겠음 고민해봐야함..
    private int endPage = 0;

    private int totalCnt;

    private int nowPage;

    private int totalPage;

    public PageUtil(Pageable pageable, int totalCount, int totalPage) {
        this.page = pageable.getPageNumber() < 0 ? NumberUtils.toInt(DEFAULT_PAGE_NUM) : pageable.getPageNumber() + 1;
        this.size = pageable.getPageSize() < 0 ? NumberUtils.toInt(DEFAULT_PAGE_SIZE) : pageable.getPageSize();
        this.sort_type = DEFAULT_SORT_TYPE;
        this.startPage = (((int) Math.ceil(((double) (pageable.getPageNumber()+1)/ PER_PAGE_SIZE))) - 1) * PER_PAGE_SIZE + 1;
        this.endPage = Math.min((startPage + PER_PAGE_SIZE - 1), totalCount);
        this.totalCnt = totalCount;
        this.nowPage = pageable.getPageNumber() + 1;
        this.totalPage = totalPage;
    }

}
