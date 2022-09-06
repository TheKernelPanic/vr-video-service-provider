package com.vrvideo.serviceprovider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(
    name = "actress",
    uniqueConstraints = {
         @UniqueConstraint(columnNames = "uuid"),
         @UniqueConstraint(columnNames = "slug")
    }
)
public class Actress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    private String name;

    @Field
    private String uuid;

    @Field
    private String slug;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @ManyToMany(mappedBy = "actresses")
    private Set<VrVideo> videos;
}
