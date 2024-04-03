package com.juchan.board.springboardjpa.api.articlecomment.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QArticleComment is a Querydsl query type for ArticleComment
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QArticleComment extends EntityPathBase<ArticleComment> {

    private static final long serialVersionUID = 1230996802L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticleComment articleComment = new QArticleComment("articleComment");

    public final com.juchan.board.springboardjpa.common.QBaseEntitiy _super = new com.juchan.board.springboardjpa.common.QBaseEntitiy(this);

    public final com.juchan.board.springboardjpa.api.article.domain.QArticle article;

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.juchan.board.springboardjpa.api.member.domain.QMember member;

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QArticleComment(String variable) {
        this(ArticleComment.class, forVariable(variable), INITS);
    }

    public QArticleComment(Path<? extends ArticleComment> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticleComment(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticleComment(PathMetadata metadata, PathInits inits) {
        this(ArticleComment.class, metadata, inits);
    }

    public QArticleComment(Class<? extends ArticleComment> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.article = inits.isInitialized("article") ? new com.juchan.board.springboardjpa.api.article.domain.QArticle(forProperty("article"), inits.get("article")) : null;
        this.member = inits.isInitialized("member") ? new com.juchan.board.springboardjpa.api.member.domain.QMember(forProperty("member")) : null;
    }

}

