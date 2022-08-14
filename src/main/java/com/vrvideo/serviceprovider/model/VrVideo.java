package com.vrvideo.serviceprovider.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Indexed
@Getter
@Setter
@NoArgsConstructor
@Table(name = "vr_video")
public class VrVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Field
    private String description;

    @Field
    private Boolean reported;

    @Field
    private int fileSize;

    @Field
    private String uuid;

    @Field
    private String originalFilename;

    @Field
    private Float durationSeconds;

    @Field
    private int viewedTimes;

    @Field
    private Date createdAt;

    @Field
    private Boolean favourite;

    @Field
    private int width;

    @Field
    private int height;

    @ManyToMany
    @JoinTable(
            name = "vr_video_actress",
            joinColumns = @JoinColumn(name = "vr_video_id"),
            inverseJoinColumns = @JoinColumn(name = "actress_id")
    )
    private Set<Actress> actresses;

    /**
     * TODO: Cascade type
     */
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private HardDrive backupOnHardDrive;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CdnHost hostedOn;
}
