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
        name = "image_vr_video",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"image_id", "vr_video_id"})
        }
)
public class ImageVrVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private VrVideo vrVideo;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Image image;
}
