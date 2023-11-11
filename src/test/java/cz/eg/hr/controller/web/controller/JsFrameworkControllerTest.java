package cz.eg.hr.controller.web.controller;

import cz.eg.hr.controller.AbstractJsFrameworkTest;
import cz.eg.hr.web.model.JsFrameworkBaseV1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class JsFrameworkControllerTest extends AbstractJsFrameworkTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void create() throws Exception {
        JsFrameworkBaseV1 data = prepareJsFrameworkBaseV1();

        mockMvc.perform(
            MockMvcRequestBuilders
                .post("/v1/frameworks")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(buildRequestBody(data))
        ).andExpect(MockMvcResultMatchers.status().isCreated())
            .andExpect(jsonPath("$.name", is(data.getName())))
            .andExpect(jsonPath("$.versions", hasSize(1)))
            .andExpect(jsonPath("$.versions[0].version", is("1.0.0")))
            .andExpect(jsonPath("$.versions[0].deprecated", is("2022-06-05")))
            .andExpect(jsonPath("$.rating", is(5)));
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                    .get("/v1/frameworks")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
            ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void update() {
        //TODO
    }

    @Test
    void update_notFound() {
        //TODO
    }

    @Test
    void update_wrongPatch() {
        //TODO
    }

    @Test
    void delete() throws Exception {
        //TODO
    }

    @Test
    void delete_notFound() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders
                .delete("/v1/frameworks/42")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}
