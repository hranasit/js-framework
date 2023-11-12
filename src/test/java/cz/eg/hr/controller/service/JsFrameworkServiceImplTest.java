package cz.eg.hr.controller.service;

import cz.eg.hr.controller.AbstractJsFrameworkTest;
import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.repository.JsFrameworkRepository;
import cz.eg.hr.service.JsFrameworkService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JsFrameworkServiceImplTest extends AbstractJsFrameworkTest {

    @Autowired
    private JsFrameworkService service;

    @MockBean
    private JsFrameworkRepository repository;

    @Test
    public void create() {
        JsFramework data = prepareJsFramework();
        BDDMockito.given(repository.save(data)).willReturn(data);

        assertEquals(data, service.create(data));
        Mockito.verify(repository, Mockito.atLeastOnce()).save(data);
    }

    @Test
    public void findAll() {
        Pageable pageable = Pageable.ofSize(10);
        List<JsFramework> expected = prepareJsFrameworks();
        Page<JsFramework> expectedPage = new PageImpl<>(expected, pageable, expected.size());
        BDDMockito.given(repository.findAll(pageable)).willReturn(expectedPage);

        assertEquals(2, service.findAll(pageable).getTotalElements());
        Mockito.verify(repository, Mockito.atLeastOnce()).findAll(pageable);
    }

    @Test
    void search() {
        //TODO every public method would have test, for negative and positive flows
    }

    @Test
    void search_empty() {
        //TODO every public method would have test, for negative and positive flows
    }

    @Test
    void update() {
        //TODO every public method would have test, for negative and positive flows
    }

    @Test
    void update_notFound() {
        //TODO every public method would have test, for negative and positive flows
    }


    @Test
    void delete() {
        //TODO every public method would have test, for negative and positive flows
    }

    @Test
    public void delete_notFound() {
        assertThrows(EntityNotFoundException.class, () -> service.delete(42L));
    }
}
