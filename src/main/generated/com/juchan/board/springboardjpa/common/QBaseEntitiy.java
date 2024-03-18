package com.juchan.board.springboardjpa.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QBaseEntitiy is a Querydsl query type for BaseEntitiy
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QBaseEntitiy extends EntityPathBase<BaseEntitiy> {

    private static final long serialVersionUID = 1339272626L;

    public static final QBaseEntitiy baseEntitiy = new QBaseEntitiy("baseEntitiy");

    public final StringPath createBy = createString("createBy");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final StringPath updateBy = createString("updateBy");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QBaseEntitiy(String variable) {
        super(BaseEntitiy.class, forVariable(variable));
    }

    public QBaseEntitiy(Path<? extends BaseEntitiy> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseEntitiy(PathMetadata metadata) {
        super(BaseEntitiy.class, metadata);
    }

}

