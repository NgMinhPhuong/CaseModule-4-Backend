package com.codegym.casemodule5.controller;

import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getALl(){
        return new ResponseEntity<>(iCategoryService.findAll() ,HttpStatus.OK);
    }
}
