package com.BoardGameAtlas.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AtlasConfig {
    @Value("${atlas.api.endpoint.prod}")
    private String apiEndpoint;

    @Value("${atlas.api.client.id}")
    private String clientId;
}
