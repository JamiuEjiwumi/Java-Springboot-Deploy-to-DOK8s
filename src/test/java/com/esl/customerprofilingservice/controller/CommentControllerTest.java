package com.esl.customerprofilingservice.controller;

import com.esl.customerprofilingservice.Dto.CommentDTO;
import com.esl.customerprofilingservice.services.CommentService;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class CommentControllerTest {

    @InjectMocks
    CommentController commentController;

    @Mock
    CommentService commentService;

    MockMvc mockMvc;

    CommentDTO commentDTO;
    Gson gson = new Gson();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
        commentDTO = new CommentDTO();
        commentDTO.setEntryId(1L);
        commentDTO.setOwner("user");
        commentDTO.setContent("some random content");
    }

    @Test
    void createComment() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/comment")
                .accept(MediaType.APPLICATION_JSON)
                .content(gson.toJson(commentDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}