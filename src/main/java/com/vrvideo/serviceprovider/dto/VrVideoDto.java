package com.vrvideo.serviceprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class VrVideoDto {

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("viewed_times")
    private int viewedTimes;

    @JsonProperty("favourite")
    private Boolean favourite;

    @JsonProperty("description")
    private String description;

    @JsonProperty("duration_seconds")
    private int durationSeconds;

    @JsonProperty("rating")
    private int rating;

    @JsonProperty("filesize")
    private long filesize;

    @JsonProperty("width")
    private int width;

    @JsonProperty("height")
    private int height;

    @JsonProperty("actresses")
    private Set<ActressDto> actresses;

    @JsonProperty("categories")
    private Set<CategoryDto> categories;

    @JsonProperty("hosted_on")
    private CdnHostDto hostedOn;

    @JsonProperty("backup_on")
    private HardDriveDto backupOn;

    @JsonProperty("images")
    private Set<ImageVrVideoDto> imagesVrVideo;

    @JsonProperty("created_at")
    private Date createdAt;
}
