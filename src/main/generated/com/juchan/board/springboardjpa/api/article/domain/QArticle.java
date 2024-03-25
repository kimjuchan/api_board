package com.juchan.board.springboardjpa.api.article.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticle is a Querydsl query type for Article
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticle extends EntityPathBase<Article> {

    private static final long serialVersionUID = 1332402776L;

    public static final QArticle article = new QArticle("article");

    public final com.juchan.board.springboardjpa.common.QBaseEntitiy _super = new com.juchan.board.springboardjpa.common.QBaseEntitiy(this);

    public final ListPath<com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment, com.juchan.board.springboardjpa.api.articlecomment.domain.QArticleComment> articleComments = this.<com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment, com.juchan.board.springboardjpa.api.articlecomment.domain.QArticleComment>createList("articleComments", com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment.class, com.juchan.board.springboardjpa.api.articlecomment.domain.QArticleComment.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath hashtag = createString("hashtag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath title = createString("title");

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QArticle(String variable) {
        super(Article.class, forVariable(variable));
    }

    public QArticle(Path<? extends Article> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticle(PathMetadata metadata) {
        super(Article.class, metadata);
    }

}

