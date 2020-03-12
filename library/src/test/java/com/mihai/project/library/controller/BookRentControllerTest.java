package com.mihai.project.library.controller;

import com.mihai.project.library.dto.book.BookDTOQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.HashSet;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class BookRentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void given_whenReturnBookRent_returnBookRent() throws Exception {
       MvcResult result =  mockMvc.perform(MockMvcRequestBuilders.get("/books")
                                .accept(MediaType.APPLICATION_JSON_VALUE))
                                .andExpect(status().isOk())
                                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                                .andReturn();
       String resultFrom = result.getResponse().getContentAsString();
       Assertions.assertNotNull(resultFrom);
    }
}
