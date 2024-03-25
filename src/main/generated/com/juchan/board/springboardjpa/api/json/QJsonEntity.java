package com.juchan.board.springboardjpa.api.json;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJsonEntity is a Querydsl query type for JsonEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QJsonEntity extends EntityPathBase<JsonEntity> {

    private static final long serialVersionUID = -1516607075L;

    public static final QJsonEntity jsonEntity = new QJsonEntity("jsonEntity");

    public final com.juchan.board.springboardjpa.common.QBaseEntitiy _super = new com.juchan.board.springboardjpa.common.QBaseEntitiy(this);

    //inherited
    public final StringPath createBy = _super.createBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath jsonTypeByMsg = createString("jsonTypeByMsg");

    public final StringPath msg = createString("msg");

    public final StringPath msgType = createString("msgType");

    //inherited
    public final StringPath updateBy = _super.updateBy;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QJsonEntity(String variable) {
        super(JsonEntity.class, forVariable(variable));
    }

    public QJsonEntity(Path<? extends JsonEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QJsonEntity(PathMetadata metadata) {
        super(JsonEntity.class, metadata);
    }

}

