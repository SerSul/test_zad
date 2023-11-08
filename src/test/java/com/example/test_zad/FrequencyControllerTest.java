package com.example.test_zad;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;



@SpringBootTest
@AutoConfigureMockMvc
class FrequencyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCalculateFrequency() throws Exception {
        String message = "hello";

        mockMvc.perform(MockMvcRequestBuilders.get("/calculateFrequency/{message}", message))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.l").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.e").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.h").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.o").value(1));
    }






    @Test
    void testCalculateFrequencyWithSpecialCharacters() throws Exception {
        String message = "a!b@c#";

        mockMvc.perform(MockMvcRequestBuilders.get("/calculateFrequency/{message}", message))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.a").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.b").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.c").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.!").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.@").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.#").value(1));
    }

}

