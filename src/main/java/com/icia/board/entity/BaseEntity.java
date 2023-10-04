package com.icia.board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    //작성시간 칼럼
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createAt;

    //수정시간 칼럼
    @UpdateTimestamp
    @Column(insertable = false)
    private LocalDateTime updateAt;
}
