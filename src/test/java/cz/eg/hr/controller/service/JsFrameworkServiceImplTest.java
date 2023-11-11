package cz.eg.hr.controller.service;

import cz.eg.hr.controller.AbstractJsFrameworkTest;
import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.repository.JsFrameworkRepository;
import cz.eg.hr.service.JsFrameworkService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class JsFrameworkServiceImplTest extends AbstractJsFrameworkTest {

    @Autowired
    private JsFrameworkService service;

    @Test
    public void create() {
        JsFramework data = prepareJsFramework();

        service.create(data);

        Optional<JsFramework> result = repository.findById(1L);
        assertTrue(result.isPresent());

        repository.delete(result.get()); //TODO
    }

    @Test
    public void findAll() {
        repository.saveAll(prepareJsFrameworks());

        Page<JsFramework> result = repository.findAll(Pageable.ofSize(10));
        assertEquals(2, result.getTotalElements());
    }
}
