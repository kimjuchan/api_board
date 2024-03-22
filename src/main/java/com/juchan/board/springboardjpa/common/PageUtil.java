package com.juchan.board.springboardjpa.common;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PageUtil {

    public static final String DEFAULT_SORT_TYPE = "id";
    private static final String DEFAULT_PAGE_NUM = "0";
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

    public PageUtil(Pageable pageable, int totalCount) {
        this.page = pageable.getPageNumber() < 0 ? NumberUtils.toInt(DEFAULT_PAGE_NUM) : pageable.getPageNumber();
        this.size = pageable.getPageSize() < 0 ? NumberUtils.toInt(DEFAULT_PAGE_SIZE) : pageable.getPageSize();
        this.sort_type = DEFAULT_SORT_TYPE;
        this.startPage = (((int) Math.ceil(((double) pageable.getPageNumber() / PER_PAGE_SIZE))) - 1) * PER_PAGE_SIZE + 1;
        this.endPage = Math.min((startPage + PER_PAGE_SIZE - 1), totalCount);
        this.totalCnt = totalCount;
        this.nowPage = pageable.getPageNumber();
    }


    public int getSize() {
        return this.size < 1 ? 1 : this.size;
    }


    //return 타입 확인 필요
    public PageRequest toPageable() {
        return PageRequest.of(this.page, getSize());
    }

    public PageRequest toPageableWithSort(Sort sort) {
        return PageRequest.of(this.page, getSize(), sort);
    }




}
