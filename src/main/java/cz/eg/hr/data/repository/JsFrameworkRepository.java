package cz.eg.hr.data.repository;

import cz.eg.hr.data.model.JsFramework;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public interface JsFrameworkRepository extends CrudRepository<JsFramework, Long> {

    Page<JsFramework> findAll(Pageable pageable);
}
