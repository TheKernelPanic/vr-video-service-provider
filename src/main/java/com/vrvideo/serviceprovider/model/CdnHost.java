package com.vrvideo.serviceprovider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Indexed
@Getter
@Setter
@NoArgsConstructor
@Table(name = "cdn_host")
public class CdnHost {

    @Id
    private int id;

    @Field
    private String address;

    @Field
    private String description;
}
