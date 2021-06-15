package com.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Getter
@PropertySource(value = "classpath:application.properties")
public class AdminConfig {
    @Value("admin.mail")
    private String adminMail;

    @Value("admin.name")
    private String adminName;
}

