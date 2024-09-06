package org.example.productcatalogservice.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date lastUpdateAt;
    private Enum status;
}
