package com.desafio.conta.util;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ContextConfiguration
public abstract class IntTestComum {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    protected MockMvc getMockMvc() {
        return mockMvc;
    }

    @BeforeEach
    void setUp() {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


}
