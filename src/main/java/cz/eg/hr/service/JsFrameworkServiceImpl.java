package cz.eg.hr.service;

import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.data.repository.JsFrameworkRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    public JsFramework update(Long id, JsFramework jsFramework) {
        throw new NotYetImplementedException();
    }

    @Override
    public void delete(Long id) {
        if (!jsFrameworkRepository.existsById(id)) {
            throw new EntityNotFoundException();
        }
        jsFrameworkRepository.deleteById(id);
    }
}
