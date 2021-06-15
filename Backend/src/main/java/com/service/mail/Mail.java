package com.service.mail;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Mail {
    private String receiver;
    private String Subject;
    private String message;
}
