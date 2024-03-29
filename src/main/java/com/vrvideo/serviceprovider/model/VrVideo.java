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
@Getter
@Setter
@NoArgsConstructor
@Table(
        name = "vr_video",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "uuid"),
                @UniqueConstraint(columnNames = "originalFilename")
        }
)
public class VrVideo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Field
    private String description;

    @Field
    private Boolean reported;

    @Field
    private long fileSize;

    @Field
    private String uuid;

    @Field
    private String originalFilename;

    @Field
    private Float durationSeconds;

    @Field
    private int rating;

    @Field
    private int viewedTimes;

    @CreationTimestamp
    private Date createdAt;

    @UpdateTimestamp
    private Date updatedAt;

    @Field
    private Boolean favourite;

    @Field
    private int width;

    @Field
    private int height;

    @Field
    private String format;

    @ManyToMany
    @JoinTable(
            name = "vr_video_actress",
            joinColumns = @JoinColumn(name = "vr_video_id"),
            inverseJoinColumns = @JoinColumn(name = "actress_id")
    )
    private Set<Actress> actresses;

    @ManyToMany
    @JoinTable(
            name = "vr_video_category",
            joinColumns = @JoinColumn(name = "vr_video_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    /**
     * TODO: Cascade type
     */
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private HardDrive backupOn;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private CdnHost hostedOn;

    @OneToMany(mappedBy = "vrVideo")
    private Set<ImageVrVideo> imagesVrVideo;
}
