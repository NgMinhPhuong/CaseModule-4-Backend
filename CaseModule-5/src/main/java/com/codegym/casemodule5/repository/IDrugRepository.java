package com.codegym.casemodule5.repository;

import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDrugRepository extends JpaRepository<Drug, Long> {

    List<Drug> findByNameLike(String name);



}
