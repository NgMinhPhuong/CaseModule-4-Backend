package com.codegym.casemodule5.repository;

import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.model.CategoryDrug;
import com.codegym.casemodule5.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IDrugCategoryRepository extends JpaRepository<CategoryDrug, Long> {

    List<CategoryDrug> findByDrug(Drug drug);
    List<CategoryDrug> findByCategory(Category category);
    void deleteByDrug(Drug drug);
    List<CategoryDrug> findByQuantityBetween(int price1, int price2);

    @Modifying
    @Query(nativeQuery = true, value = "update category_drug set quantity = ?1 where drug_id = ?2")
    void upddateByDrug(int quantity, Long drug_id);
}
