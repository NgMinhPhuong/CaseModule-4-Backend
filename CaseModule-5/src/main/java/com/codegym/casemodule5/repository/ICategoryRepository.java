package com.codegym.casemodule5.repository;

import com.codegym.casemodule5.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ICategoryRepository extends JpaRepository<Category, Long> {

}
