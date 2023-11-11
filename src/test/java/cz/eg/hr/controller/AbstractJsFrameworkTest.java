package cz.eg.hr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.model.JsFrameworkVersion;
import cz.eg.hr.data.repository.JsFrameworkRepository;
import cz.eg.hr.web.model.JsFrameworkBaseV1;
import cz.eg.hr.web.model.JsFrameworkVersionV1;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootTest
public class AbstractJsFrameworkTest {

    @Autowired
    protected JsFrameworkRepository repository;

    @BeforeEach
    public void init() {
        repository.saveAll(prepareJsFrameworks());
    }

    protected JsFrameworkBaseV1 prepareJsFrameworkBaseV1() {
        List<JsFrameworkVersionV1> versions = Collections.singletonList(
            new JsFrameworkVersionV1("1.0.0", Date.valueOf("2022-06-05"))
        );
        JsFrameworkBaseV1 data = new JsFrameworkBaseV1("Angular", versions, 5);

        return data;
    }

    protected JsFramework prepareJsFramework() {
        List<JsFrameworkVersion> versions = Collections.singletonList(
            new JsFrameworkVersion("1.0.0", Date.valueOf("2022-06-05"))
        );
        JsFramework data = new JsFramework(1L, "Angular", versions, 5);

        return data;
    }

    protected List<JsFramework> prepareJsFrameworks() {
        List<JsFramework> result = new ArrayList<>();

        List<JsFrameworkVersion> versions = Collections.singletonList(
            new JsFrameworkVersion("2.0.0", Date.valueOf("2022-06-05"))
        );
        JsFramework data = new JsFramework(2L, "React", versions, 5);

        result.add(data);

        List<JsFrameworkVersion> versions2 = Collections.singletonList(
            new JsFrameworkVersion("1.5.0", Date.valueOf("2020-06-05"))
        );
        JsFramework data2 = new JsFramework(3L, "Ember.js", versions2, 1);

        result.add(data2);

        return result;
    }

    protected String buildRequestBody(Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(object);
    }
}
