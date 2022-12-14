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
public class ActressDto {

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("images")
    private Set<ImageActressDto> imagesActress;

    @JsonProperty("created_at")
    private Date createdAt;
}
