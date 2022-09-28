package com.vrvideo.serviceprovider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "image_actress",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"image_id", "actress_id"})
        }
)
public class ImageActress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Actress actress;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Image image;
}
