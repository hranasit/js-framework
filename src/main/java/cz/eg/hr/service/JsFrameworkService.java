package cz.eg.hr.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import cz.eg.hr.data.model.JsFramework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JsFrameworkService {
    /**
     * NOTE: if there would be some complex logic/data enrichment/mapping,
     * service layer would use service objectsand map them between data object and api objects
     * - this is very simplified and mapping is only between data and api objects
     */

    JsFramework create(JsFramework jsFramework);

    Page<JsFramework> findAll(Pageable pageable);

    void update(Long id, JsonPatch patch) throws JsonPatchException, JsonProcessingException;

    void delete(Long id);
}
