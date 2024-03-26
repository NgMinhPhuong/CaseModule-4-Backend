package com.codegym.casemodule5.service.impl;

import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.repository.ICategoryRepository;
import com.codegym.casemodule5.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> findAll() {
        return iCategoryRepository.findAll();
    }


}
