package cz.eg.hr.controller.service;

import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.model.JsFrameworkVersion;
import cz.eg.hr.data.repository.JsFrameworkRepository;
import cz.eg.hr.service.JsFrameworkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JsFrameworkServiceImplTest {

    @Autowired
    private JsFrameworkService service;

    @Autowired
    private JsFrameworkRepository repository;

    @Test
    public void create() {
        List<JsFrameworkVersion> versions = Collections.singletonList(
            new JsFrameworkVersion("1.0.0", Date.valueOf("2022-06-05"))
        );
        JsFramework data = new JsFramework(1L,"Angular", versions, 5);

        service.create(data);

        Optional<JsFramework> result = repository.findById(1L);
        assertTrue(result.isPresent());
    }

}
