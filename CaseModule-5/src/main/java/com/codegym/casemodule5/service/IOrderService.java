package com.codegym.casemodule5.service;

import com.codegym.casemodule5.dto.order.OrderDtoRepsonse;

import java.util.List;

public interface IOrderService {
    List<OrderDtoRepsonse> findAll();
    OrderDtoRepsonse findById(Long id);
//    OrderDtoRepsonse add(OrderDtoRepsonse orderDto);
}
