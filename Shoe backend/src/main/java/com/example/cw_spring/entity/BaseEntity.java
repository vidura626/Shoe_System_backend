package com.example.cw_spring.entity;


import com.example.cw_spring.enums.Deleted;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.ZonedDateTime;

@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements SuperEntity {
    @CreatedBy
    @Column(name = "CREATED_BY", length = 50, updatable = false)
    private String createdBy;

    @Column(name = "CREATED_DATE_TIME", updatable = false,columnDefinition="TIMESTAMP")
    @CreationTimestamp
    private ZonedDateTime createdDateTime;

    @LastModifiedBy
    @Column(name = "MODIFIED_BY", length = 50)
    private String modifiedBy;

    @Column(name = "MODIFIED_DATE_TIME", columnDefinition="TIMESTAMP")
    @UpdateTimestamp
    private ZonedDateTime modifiedDateTime;

    @Column(name = "IS_DELETED")
    private Deleted isDeleted = Deleted.NO;
}
