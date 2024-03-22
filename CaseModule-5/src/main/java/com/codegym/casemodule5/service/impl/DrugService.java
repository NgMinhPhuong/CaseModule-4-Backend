package com.codegym.casemodule5.service.impl;

import com.codegym.casemodule5.dto.DrugDto;
import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.model.CategoryDrug;
import com.codegym.casemodule5.model.Drug;
import com.codegym.casemodule5.repository.ICategoryRepository;
import com.codegym.casemodule5.repository.IDrugCategoryRepository;
import com.codegym.casemodule5.repository.IDrugRepository;
import com.codegym.casemodule5.service.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Transactional
@Service
public class DrugService implements IDrugService {
    @Autowired
    private IDrugRepository iDrugRepository;

    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    private IDrugCategoryRepository iDrugCategoryRepository;
    @Override
    public List<DrugDto> findAll() {
        List<DrugDto> drugDtos = new ArrayList<>();
        List<Drug> drugs = iDrugRepository.findAll();
        int quantity = 0;
        for(Drug drug1 : drugs){
            drugDtos.add(buildDTOResponseFromDrug(drug1));
        }
        return drugDtos;
    }

    @Override
    public DrugDto add(DrugDto drugDto) {
        List<Category> list = new ArrayList<>();
        Drug drug = toDrug(drugDto);
        Drug drug1 = iDrugRepository.save(drug);
        for(Category c : drugDto.getCategories()){
            Category category = iCategoryRepository.findById(c.getId()).get();
            list.add(category);
            CategoryDrug categoryDrug = new CategoryDrug();
            categoryDrug.setDrug(drug);
            categoryDrug.setCategory(category);
            categoryDrug.setQuantity(drugDto.getQuantity());
            iDrugCategoryRepository.save(categoryDrug);
        }
        drugDto.setCategories(list);
        drugDto.setId(drug1.getId());
        return drugDto;
    }

    @Override
    public DrugDto update(DrugDto drugDto) {
        Drug drug = toDrug(drugDto);
        iDrugRepository.save(drug);
        iDrugCategoryRepository.deleteByDrug(toDrug(drugDto));
        for(Category c : drugDto.getCategories()){
            Category category = iCategoryRepository.findById(c.getId()).get();
            CategoryDrug categoryDrug = new CategoryDrug();
            categoryDrug.setDrug(drug);
            categoryDrug.setCategory(category);
            categoryDrug.setQuantity(drugDto.getQuantity());
            iDrugCategoryRepository.save(categoryDrug);
        }
        List<Category> categories = new ArrayList<>();
        for(Category c : drugDto.getCategories()){
            Category category = iCategoryRepository.findById(c.getId()).get();
            categories.add(category);
        }
        drugDto.setCategories(categories);
        return drugDto;
    }

    @Override
    public Drug delete(Long id) {
        Optional<Drug> optional = iDrugRepository.findById(id);
        if(optional.isPresent()) {
            iDrugCategoryRepository.deleteByDrug(optional.get());
            iDrugRepository.delete(optional.get());
            return optional.get();
        }
        return null;
    }

    @Override
    public DrugDto findById(Long id) {
        Optional<Drug> optional = iDrugRepository.findById(id);
        if(optional.isEmpty()) return null;
        return  (buildDTOResponseFromDrug(optional.get()));
    }

    @Override
    public List<DrugDto> findByCategory(Long id) {
        List<DrugDto> drugDtos = new ArrayList<>();
        Category category = new Category();
        category.setId(id);
        List<CategoryDrug> categoryDrugs = iDrugCategoryRepository.findByCategory(category);
        for(CategoryDrug categoryDrug : categoryDrugs){
            Drug drug = categoryDrug.getDrug();
            List<Category> categories = new ArrayList<>();
            Category category1 = categoryDrug.getCategory();
            categories.add(category1);
            drugDtos.add(buildDTOResponseFromDrug(drug));
        }
        return drugDtos;
    }

    @Override
    public List<DrugDto> findByName(String name) {
        List<DrugDto> drugDtos = new ArrayList<>();

        List<Drug> drugs = iDrugRepository.findByNameLike("%" + name + "%");
        for(Drug drug1 : drugs){
            drugDtos.add(buildDTOResponseFromDrug(drug1));
        }
        return drugDtos;
    }

    @Override
    public List<DrugDto> findByPrice(int priceTop, int priceBot) {
        List<DrugDto> drugDtos = new ArrayList<>();
        List<CategoryDrug> categoryDrugs = iDrugCategoryRepository.findByQuantityBetween(priceTop, priceBot);
        List<Drug> drugs = new ArrayList<>();
        List<Long> check = new ArrayList<>();
        for(CategoryDrug categoryDrug : categoryDrugs){
            if(!check.contains(categoryDrug.getDrug().getId())){
                drugs.add(categoryDrug.getDrug());
                check.add(categoryDrug.getDrug().getId());
            }
        }
        for(Drug drug1 : drugs){
            drugDtos.add(buildDTOResponseFromDrug(drug1));
        }
        return drugDtos;
    }

    @Override
    public List<DrugDto> findAllOrderByPrice(Sort sort){
        List<Drug> drugs = iDrugRepository.findAll(sort);
        List<DrugDto> drugDtos = new ArrayList<>();
        for(Drug d : drugs){
            drugDtos.add(buildDTOResponseFromDrug(d));
        }
        return drugDtos;
    }

     public DrugDto buildDTOResponseFromDrug(Drug drug){
        int quantity = 0;
         List<Category> categories = new ArrayList<>();
         List<CategoryDrug> categoryDrugs = iDrugCategoryRepository.findByDrug(drug);
         for(CategoryDrug categoryDrug : categoryDrugs) {
             Category category = iCategoryRepository.findById(categoryDrug.getCategory().getId()).get();
             categories.add(category);
             quantity = categoryDrug.getQuantity();
         }
        DrugDto drugDto = new DrugDto();
        drugDto.setId(drug.getId());
        drugDto.setName(drug.getName());
        drugDto.setPrice(drug.getPrice());
        drugDto.setExpire(drug.getExpire());
        drugDto.setCategories(categories);
        drugDto.setImage(drug.getImage());
        drugDto.setQuantity(quantity);
        drugDto.setImage(drugDto.getImage());
        return drugDto;
     }

    public Drug toDrug(DrugDto drugDto){
        Drug drug = new Drug();
        drug.setId(drugDto.getId());
        drug.setName(drugDto.getName());
        drug.setPrice(drugDto.getPrice());
        drug.setExpire(drugDto.getExpire());
        drug.setImage(drugDto.getImage());
        return drug;
    }

}
