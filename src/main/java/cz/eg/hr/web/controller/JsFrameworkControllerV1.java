package cz.eg.hr.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import cz.eg.hr.data.model.JsFramework;
import cz.eg.hr.service.JsFrameworkService;
import cz.eg.hr.web.mapper.JsFrameworkBaseMapper;
import cz.eg.hr.web.mapper.JsFrameworkMapper;
import cz.eg.hr.web.model.JsFrameworkBaseV1;
import cz.eg.hr.web.model.JsFrameworkV1;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("v1/frameworks")
public class JsFrameworkControllerV1 {

    @Autowired
    private JsFrameworkService jsFrameworkService;


    @PostMapping
    public ResponseEntity<EntityModel<JsFrameworkV1>> create(@RequestBody @Valid JsFrameworkBaseV1 data) {
        JsFramework jsFramework = JsFrameworkBaseMapper.toDomain(data);
        JsFramework created = jsFrameworkService.create(jsFramework);
        JsFrameworkV1 response = JsFrameworkMapper.toModel(created);

        Link selfLink = WebMvcLinkBuilder.linkTo(methodOn(JsFrameworkControllerV1.class).create(data)).withSelfRel();
        /**
         * - links to other methods can be added depending on flow
         * - would be on other EPs in controller too, except delete
         */

        return new ResponseEntity<>(EntityModel.of(response, selfLink), HttpStatus.CREATED);
    }

    //TODO fulltext somewhere
    @GetMapping
    public Page<JsFrameworkV1> list(Pageable pageable) {
        return jsFrameworkService.findAll(pageable).map(JsFrameworkMapper::toModel);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<JsFrameworkV1> update(@PathVariable Long id, @RequestBody JsonPatch data) throws JsonPatchException, JsonProcessingException {
        JsFramework updated = jsFrameworkService.update(id, data);
        return new ResponseEntity<>(JsFrameworkMapper.toModel(updated), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        jsFrameworkService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
