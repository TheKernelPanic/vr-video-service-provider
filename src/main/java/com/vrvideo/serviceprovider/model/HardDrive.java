package com.vrvideo.serviceprovider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Indexed
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "hard_drive",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code")
        }
)
public class HardDrive {

    @Id
    private int id;

    @Field
    private String description;

    @Field
    private String code;
}
