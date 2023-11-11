package cz.eg.hr.service;

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

    JsFramework update(Long id, JsFramework jsFramework);

    void delete(Long id);
}
