package com.codegym.casemodule5.dto.order;

import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.model.Drug;
import com.codegym.casemodule5.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDtoRequest {
    private Long id_drug;
    private int quantity;

}
