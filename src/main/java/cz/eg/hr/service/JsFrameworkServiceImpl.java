package cz.eg.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.repository.JsFrameworkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JsFrameworkServiceImpl implements JsFrameworkService {

    @Autowired
    private JsFrameworkRepository jsFrameworkRepository;

    @Override
    public JsFramework create(JsFramework jsFramework) {
        return jsFrameworkRepository.save(jsFramework);
    }

    @Override
    public Page<JsFramework> findAll(Pageable pageable) {
        return jsFrameworkRepository.findAll(pageable);
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
