package cz.eg.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.repository.JsFrameworkRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class JsFrameworkServiceImpl implements JsFrameworkService {

    @Autowired
    private JsFrameworkRepository jsFrameworkRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public JsFramework create(JsFramework jsFramework) {
        return jsFrameworkRepository.save(jsFramework);
    }

    @Override
    public Page<JsFramework> findAll(Pageable pageable) {
        return jsFrameworkRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public List<JsFramework> search(String search) throws InterruptedException {
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
        fullTextEntityManager.createIndexer().startAndWait();

        QueryBuilder queryBuilder = fullTextEntityManager
            .getSearchFactory()
            .buildQueryBuilder()
            .forEntity(JsFramework.class)
            .get();
        Query fullTextQuery = queryBuilder
            .keyword()
            .onField("name")
            .matching(search)
            .createQuery();

        return fullTextEntityManager.createFullTextQuery(fullTextQuery).getResultList();
    }


    @Override
    public JsFramework update(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        Optional<JsFramework> origin = jsFrameworkRepository.findById(id);
        if (origin.isEmpty()) {
            throw new EntityNotFoundException();
        }

        JsFramework jsFramework = applyPatch(patch, origin.get());

        return jsFrameworkRepository.save(jsFramework);
    }

    @Override
    public void delete(Long id) {
        if (!jsFrameworkRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        jsFrameworkRepository.deleteById(id);
    }

    private JsFramework applyPatch(JsonPatch patch, JsFramework jsFramework) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(jsFramework, JsonNode.class));
        return objectMapper.treeToValue(patched, JsFramework.class);
    }
}
