package com.vrvideo.serviceprovider.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("canonical_name")
    private String canonicalName;
}
