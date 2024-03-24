package com.juchan.board.springboardjpa.api.article.repository;

import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.common.search.SearchDto;
import com.juchan.board.springboardjpa.common.search.SearchType;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

import static com.juchan.board.springboardjpa.api.article.domain.QArticle.article;

public class ArticleCustomRepositoryImpl implements ArticleCustomRepository{

    private final JPAQueryFactory queryFactory;

    public ArticleCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Article> search(SearchDto searchDto,Pageable pageable) {

        List<Article> list = queryFactory
                .selectFrom(article)
                .where(setSearchForm(searchDto))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(article.id.asc())
                //fetch :
                .fetch();

        JPAQuery<Long> count = queryFactory
                .select(article.count())
                .from(article)
                .where(setSearchForm(searchDto));

        return PageableExecutionUtils.getPage(list, pageable, count::fetchOne);
    }

    //검색 type에 따른 where절 setting
    public BooleanBuilder setSearchForm(SearchDto searchDto){

        if (Objects.equals(searchDto.getType(), SearchType.TITLE.toString())) {
            return titleEq(searchDto.getKeyword());
        }
        else if(Objects.equals(searchDto.getType(), SearchType.CONTENT.toString())) {
            return contentEq(searchDto.getKeyword());
        }
        else {
            return createdByEq(searchDto.getKeyword());
        }
    }

    //3가지 검색 조건  nullSafeBuilder method를 통해서 Null chk
    //contains => %keyword%
    BooleanBuilder titleEq(String title) {
        return nullSafeBuilder(() -> article.title.contains(title));
    }
    BooleanBuilder contentEq(String content) {
        return nullSafeBuilder(() -> article.content.contains(content));
    }
    BooleanBuilder createdByEq(String createdBy) {
        return nullSafeBuilder(() -> article.createBy.eq(createdBy));
    }

    BooleanBuilder nullSafeBuilder(Supplier<BooleanExpression> f) {
        try {
            return new BooleanBuilder(f.get());
        } catch (Exception e) {
            return new BooleanBuilder();
        }
    }

}
