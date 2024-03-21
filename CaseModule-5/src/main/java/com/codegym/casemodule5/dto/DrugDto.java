package com.codegym.casemodule5.dto;

import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.model.Drug;
import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DrugDto {
    private  Long id;
    private String name;
    private int price;
    private int quantity;
    private List<Category> categories;
    private String expire;

}
