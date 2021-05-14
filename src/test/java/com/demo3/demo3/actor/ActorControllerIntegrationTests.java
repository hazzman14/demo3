package com.demo3.demo3.actor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ActorControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void testGetActorAllValid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/actors/all")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetActorByIdValid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/actors/byid/"+1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testGetActorByIdInvalid() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/actors/byid/"+0.1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateValidActor() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/actors/create")
                .content(asJsonString(new Actor("Harryyyy","Phillipsss")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());

    }

    @Test
    public void testCreateInvalidActor() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/actors/create")
                .content(asJsonString(new Actor("123","123")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateEmptyActor() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/api/actors/create")
                .content(asJsonString(new Actor(" "," ")))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testDeleteActor() throws Exception
    {
        mockMvc.perform( MockMvcRequestBuilders
                .delete("/api/actors/",205))
                .andExpect(status().isAccepted());
    }
}
