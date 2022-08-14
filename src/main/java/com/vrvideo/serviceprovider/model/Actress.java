package com.vrvideo.serviceprovider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "actress")
public class Actress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Field
    private String name;

    @Field
    private String uuid;

    @Field
    private String slug;

    @ManyToMany(mappedBy = "actresses")
    private Set<VrVideo> videos;
}
