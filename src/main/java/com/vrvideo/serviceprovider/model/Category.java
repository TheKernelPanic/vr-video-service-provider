package com.vrvideo.serviceprovider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(
    name = "category",
    uniqueConstraints = {
            @UniqueConstraint(columnNames = "slug"),
            @UniqueConstraint(columnNames = "canonicalName")
    }
)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    private String slug;

    @Field
    private String canonicalName;

    @CreationTimestamp
    private Date createdAt;
}
