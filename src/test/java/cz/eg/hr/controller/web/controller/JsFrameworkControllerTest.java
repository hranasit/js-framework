package cz.eg.hr.controller.web.controller;

import com.jayway.jsonpath.JsonPath;
import cz.eg.hr.controller.AbstractJsFrameworkTest;
import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.web.model.JsFrameworkBaseV1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class JsFrameworkControllerTest extends AbstractJsFrameworkTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @BeforeEach
    public void init() {
        repository.saveAll(prepareJsFrameworks());
        //TODO also should be handled cleaning db when not using mocks
    }

    @Test
    void contextLoads() {
    }

    @Test
    void create() throws Exception {
        JsFrameworkBaseV1 data = prepareJsFrameworkBaseV1();

        MvcResult result = mockMvc.perform(
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
            .andExpect(jsonPath("$.rating", is(5)))
            .andReturn();

        Integer id = JsonPath.read(result.getResponse().getContentAsString(), "$.id");
        Optional<JsFramework> saved = transactionTemplate.execute(status -> repository.findById(id.longValue()));
        assertTrue(saved.isPresent());
    }

    @Test
    void create_invalidRating() throws Exception {
        JsFrameworkBaseV1 data = prepareJsFrameworkBaseV1();
        data.setRating(15);

        mockMvc.perform(
                MockMvcRequestBuilders
                    .post("/v1/frameworks")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(buildRequestBody(data))
            ).andExpect(MockMvcResultMatchers.status().isBadRequest());
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
    void search() throws Exception {
        mockMvc.perform(
            MockMvcRequestBuilders
                .get("/v1/frameworks")
                .queryParam("search","Rea")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void search_empty() {
        //TODO
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
    void delete() {
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

        Optional<JsFramework> deleted = transactionTemplate.execute(status -> repository.findById(42L));
        assertTrue(deleted.isEmpty());
    }
}
