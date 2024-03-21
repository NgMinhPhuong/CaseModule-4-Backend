package com.codegym.casemodule5.service.impl;

import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.repository.ICategoryRepository;
import com.codegym.casemodule5.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Override
    public List<Category> getCategory(List<Category> categories) {
        List<Category> lists = new ArrayList<>();

        for(Category c : categories){
            Category category = iCategoryRepository.findById(c.getId()).get();
            lists.add(category);
        }
        return lists;
    }
}
