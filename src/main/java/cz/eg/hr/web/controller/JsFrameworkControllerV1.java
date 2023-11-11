package cz.eg.hr.web.controller;

import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.service.JsFrameworkService;
import cz.eg.hr.web.mapper.JsFrameworkBaseMapper;
import cz.eg.hr.web.mapper.JsFrameworkMapper;
import cz.eg.hr.web.model.JsFrameworkBaseV1;
import cz.eg.hr.web.model.JsFrameworkV1;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.lang.annotation.Before;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("v1/frameworks")
public class JsFrameworkControllerV1 {
    //TODO error handling, status codes

    @Autowired
    private JsFrameworkService jsFrameworkService;


    @PostMapping
    public ResponseEntity<JsFrameworkV1> create(@RequestBody JsFrameworkBaseV1 data) {
        JsFramework jsFramework = JsFrameworkBaseMapper.toDomain(data);
        JsFramework created = jsFrameworkService.create(jsFramework);
        return new ResponseEntity<>(JsFrameworkMapper.toModel(created), HttpStatus.CREATED);
    }

    //TODO fulltext somewhere
    @GetMapping
    public Page<JsFrameworkV1> list(Pageable pageable) {
        return jsFrameworkService.findAll(pageable).map(JsFrameworkMapper::toModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JsFrameworkV1> update(@PathVariable Long id, @RequestBody JsFrameworkBaseV1 data) {
//        return new jsFrameworkService.update(id, data);
        throw new NotYetImplementedException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try{
            jsFrameworkService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
