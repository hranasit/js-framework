package cz.eg.hr.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.rest.Errors;
import cz.eg.hr.rest.ValidationError;
import cz.eg.hr.service.JsFrameworkService;
import cz.eg.hr.web.mapper.JsFrameworkBaseMapper;
import cz.eg.hr.web.mapper.JsFrameworkMapper;
import cz.eg.hr.web.model.JsFrameworkBaseV1;
import cz.eg.hr.web.model.JsFrameworkV1;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("v1/frameworks")
public class JsFrameworkControllerV1 {

    @Autowired
    private JsFrameworkService jsFrameworkService;


    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid JsFrameworkBaseV1 data, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getErrors(bindingResult));
        }

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
    public ResponseEntity<JsFrameworkV1> update(@PathVariable Long id, @RequestBody JsonPatch data) {
        try {
            jsFrameworkService.update(id, data);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
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

    private Errors getErrors(BindingResult bindingResult) {
        return new Errors(
            bindingResult.getFieldErrors().stream()
                .map(e -> new ValidationError(e.getField(), e.getDefaultMessage()))
                .collect(Collectors.toList())
        );
    }
}
