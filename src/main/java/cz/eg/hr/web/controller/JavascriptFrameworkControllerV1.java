package cz.eg.hr.web.controller;

import cz.eg.hr.data.JavascriptFramework;
import cz.eg.hr.data.repository.JavascriptFrameworkRepository;
import cz.eg.hr.web.model.JsFrameworkV1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/frameworks")
public class JavascriptFrameworkControllerV1 {
    //TODO error handling, status codes

    //TODO use service
    private final JavascriptFrameworkRepository repository;

    @Autowired
    public JavascriptFrameworkControllerV1(JavascriptFrameworkRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<JsFrameworkV1> create(@RequestBody JsFrameworkV1 data) {
        JsFramework created = repository.create(data);
        return new ResponseEntity<>(JsFrameworkV1.valueOf(created.getId()), HttpStatus.CREATED);
    }

    //TODO fulltext somewhere
    @GetMapping
    public Page<JavascriptFramework> list(Pageable pageable) {
        return new repository.findAll(pageable);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JsFrameworkV1> update(@RequestParam Long id, @RequestBody JsFrameworkV1 data) {
        return new repository.update(id, data);
    }

    @DeleteMapping("/{id}")
    //TODO
    public ResponseEntity<> delete(@RequestParam Long id) {
        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
