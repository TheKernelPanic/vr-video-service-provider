package com.vrvideo.serviceprovider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "cdn_host",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "address"),
                @UniqueConstraint(columnNames = "description")
        }
)
public class CdnHost {

    @Id
    private int id;

    @Field
    private String address;

    @Field
    private String description;
}
