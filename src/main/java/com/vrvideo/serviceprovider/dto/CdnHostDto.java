package com.vrvideo.serviceprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CdnHostDto {

    @JsonProperty("address")
    private String address;

    @JsonProperty("description")
    private String description;
}
