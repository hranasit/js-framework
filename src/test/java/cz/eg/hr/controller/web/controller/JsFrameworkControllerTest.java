package cz.eg.hr.controller.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.eg.hr.web.model.JsFrameworkBaseV1;
import cz.eg.hr.web.model.JsFrameworkVersionV1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
class JsFrameworkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void create() throws Exception {
        List<JsFrameworkVersionV1> versions = Collections.singletonList(
            new JsFrameworkVersionV1("1.0.0", Date.valueOf("2022-06-05"))
        );
        JsFrameworkBaseV1 data = new JsFrameworkBaseV1("Angular", versions, 5);

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

    private String buildRequestBody(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }

}
