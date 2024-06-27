package com.enviro.assessment.grad001.katlegomokgale.controller;

import com.enviro.assessment.grad001.katlegomokgale.model.WasteCategory;
import com.enviro.assessment.grad001.katlegomokgale.service.WasteCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@Validated
public class WasteCategoryController {
    @Autowired
    private WasteCategoryService service;

    @GetMapping
    public List<WasteCategory> getAllCategories() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WasteCategory> getCategoryById(@PathVariable Long id) {
        WasteCategory category = service.getCategoryById(id);
        if (category != null) {
            return new ResponseEntity<>(category, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public WasteCategory createCategory(@Valid @RequestBody WasteCategory category) {
        return service.saveCategory(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WasteCategory> updateCategory(@PathVariable Long id, @Valid @RequestBody WasteCategory category) {
        WasteCategory existingCategory = service.getCategoryById(id);
        if (existingCategory != null) {
            category.setId(id);
            return new ResponseEntity<>(service.saveCategory(category), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        service.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
