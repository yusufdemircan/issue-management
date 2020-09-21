package com.yusufdemircan.issuemanagement.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass

public abstract class BaseEntity implements Serializable {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "created_by",length = 100)
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private Date updateAt;
    @Column(name = "update_by",length = 100)
    private String updateBy;
    @Column(name = "status")
    private Boolean status;
}
