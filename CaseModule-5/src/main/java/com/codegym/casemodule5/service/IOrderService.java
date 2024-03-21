package com.codegym.casemodule5.service;

import com.codegym.casemodule5.dto.DrugDto;
import com.codegym.casemodule5.model.Category;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IOrderService {
    List<Category> getCategory(List<Category> categories);

}
