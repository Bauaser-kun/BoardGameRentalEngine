package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;

@SpringJUnitWebConfig
@WebMvcTest
public class DomainControllerTestSuite {
    @Autowired
    private MockMvc mockMvc;


}
