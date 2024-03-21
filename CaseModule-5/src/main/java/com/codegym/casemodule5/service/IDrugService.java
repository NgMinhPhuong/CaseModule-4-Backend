package com.codegym.casemodule5.service;

import com.codegym.casemodule5.dto.DrugDto;
import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.model.Drug;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface IDrugService {
    List<DrugDto> findAll();
    DrugDto add(DrugDto drugDto);
    DrugDto update(DrugDto drugDto);
    Drug delete(Long id);
    DrugDto findById(Long id);
    List<DrugDto> findByCategory(Long id);
    List<DrugDto> findByName(String name);
    List<DrugDto> findByPrice(int priceTop, int priceBot);
    List<DrugDto> findAllOrderByPrice(Sort sort);
}
