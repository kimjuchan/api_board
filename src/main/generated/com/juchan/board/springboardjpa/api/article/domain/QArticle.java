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

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QArticle article = new QArticle("article");

    public final com.juchan.board.springboardjpa.common.QBaseEntitiy _super = new com.juchan.board.springboardjpa.common.QBaseEntitiy(this);

    public final ListPath<com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment, com.juchan.board.springboardjpa.api.articlecomment.domain.QArticleComment> articleComments = this.<com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment, com.juchan.board.springboardjpa.api.articlecomment.domain.QArticleComment>createList("articleComments", com.juchan.board.springboardjpa.api.articlecomment.domain.ArticleComment.class, com.juchan.board.springboardjpa.api.articlecomment.domain.QArticleComment.class, PathInits.DIRECT2);

    public final ListPath<com.juchan.board.springboardjpa.api.articleimg.doamain.ArticleImg, com.juchan.board.springboardjpa.api.articleimg.doamain.QArticleImg> articleImgs = this.<com.juchan.board.springboardjpa.api.articleimg.doamain.ArticleImg, com.juchan.board.springboardjpa.api.articleimg.doamain.QArticleImg>createList("articleImgs", com.juchan.board.springboardjpa.api.articleimg.doamain.ArticleImg.class, com.juchan.board.springboardjpa.api.articleimg.doamain.QArticleImg.class, PathInits.DIRECT2);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath hashtag = createString("hashtag");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.juchan.board.springboardjpa.api.member.domain.QMember member;

    public final ListPath<com.juchan.board.springboardjpa.api.hashtag.domain.TagMapping, com.juchan.board.springboardjpa.api.hashtag.domain.QTagMapping> tagMappings = this.<com.juchan.board.springboardjpa.api.hashtag.domain.TagMapping, com.juchan.board.springboardjpa.api.hashtag.domain.QTagMapping>createList("tagMappings", com.juchan.board.springboardjpa.api.hashtag.domain.TagMapping.class, com.juchan.board.springboardjpa.api.hashtag.domain.QTagMapping.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QArticle(String variable) {
        this(Article.class, forVariable(variable), INITS);
    }

    public QArticle(Path<? extends Article> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QArticle(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QArticle(PathMetadata metadata, PathInits inits) {
        this(Article.class, metadata, inits);
    }

    public QArticle(Class<? extends Article> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.juchan.board.springboardjpa.api.member.domain.QMember(forProperty("member")) : null;
    }

}

