package com.juchan.board.springboardjpa.common;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
//해당 부분은  Spring Data Jpa에서 제공되는 어노테이션
@EntityListeners(AuditingEntityListener.class)
//Entity 클래스에서  해당 Entity가 아닌 클래스 정보를 상속받기 위해 필요함.
@MappedSuperclass
public class BaseEntitiy {

    @Column(updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(length = 100)
    private String createBy;

    @LastModifiedDate
    @Column(updatable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(length = 100)
    private String updateBy;
}
