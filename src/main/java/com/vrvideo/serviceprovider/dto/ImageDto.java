package com.vrvideo.serviceprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("directory")
    private String directory;

    @JsonProperty("filename")
    private String filename;
}
