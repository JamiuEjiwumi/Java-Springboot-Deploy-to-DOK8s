package com.esl.customerprofilingservice.controller;

import com.esl.customerprofilingservice.Dto.EntriesDTO;
import com.esl.customerprofilingservice.services.EntriesService;
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
class EntriesControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private EntriesController entriesController;

    @Mock
    private EntriesService entriesService;

    EntriesDTO entriesDTO;
    Gson gson = new Gson();

    @BeforeEach
    void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(entriesController).build();
        entriesDTO = new EntriesDTO();
        entriesDTO.setOwner("TheOwner");
        entriesDTO.setCustomerIdentifier("2");
        entriesDTO.setEntry("Good customer");
    }

    @Test
    void createEntries() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.post("/entries")
                .contentType(MediaType.APPLICATION_JSON).content(gson.toJson(entriesDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void fetchEntries() throws Exception {


        mockMvc.perform(MockMvcRequestBuilders.get("/entries/filter")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}