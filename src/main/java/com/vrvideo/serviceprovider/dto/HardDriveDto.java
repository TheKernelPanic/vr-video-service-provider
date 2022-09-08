package com.vrvideo.serviceprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class HardDriveDto {

    @JsonProperty("description")
    private String description;

    @JsonProperty("code")
    private String code;
}
