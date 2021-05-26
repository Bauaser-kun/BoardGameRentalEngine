package com.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AtlasForumPostDto {
    private String title;
    private URI post_url;
}
