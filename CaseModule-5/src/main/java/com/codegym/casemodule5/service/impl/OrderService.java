package com.codegym.casemodule5.service.impl;

import com.codegym.casemodule5.dto.order.OrderDtoRepsonse;
import com.codegym.casemodule5.model.Category;
import com.codegym.casemodule5.model.CategoryDrug;
import com.codegym.casemodule5.model.Order;
import com.codegym.casemodule5.repository.IDrugCategoryRepository;
import com.codegym.casemodule5.repository.IOrderRepository;
import com.codegym.casemodule5.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {
    @Autowired
    private IOrderRepository iOrderRepository;

    @Autowired
    private IDrugCategoryRepository iDrugCategoryRepository;
    @Override
    public List<OrderDtoRepsonse> findAll() {
       List<OrderDtoRepsonse> orderDtos = new ArrayList<>();
       List <Order> orders = iOrderRepository.findAll();
       for(Order o : orders){
           orderDtos.add(buildDTOResponseFromOrder(o));
       }
       return orderDtos;
    }

    @Override
    public OrderDtoRepsonse findById(Long id) {
        Optional<Order> optional = iOrderRepository.findById(id);
        if(optional.isPresent()){
            Order order = optional.get();
            return (buildDTOResponseFromOrder(order));
        }
        return null;
    }

//    @Override
//    public OrderDtoRepsonse add(OrderDtoRepsonse orderDto) {
//        CategoryDrug categoryDrug = iDrugCategoryRepository.
//    }

    public OrderDtoRepsonse buildDTOResponseFromOrder(Order order){
        List<Category> categories = new ArrayList<>();
        List<CategoryDrug> categoryDrugs = iDrugCategoryRepository.findByDrug(order.getCategoryDrug().getDrug());
        for(CategoryDrug c : categoryDrugs){
            categories.add(c.getCategory());
        }
        OrderDtoRepsonse orderDto = new OrderDtoRepsonse();
        orderDto.setId(order.getId());
        orderDto.setName(order.getCategoryDrug().getDrug().getName());
        orderDto.setCategories(categories);
        orderDto.setPrice(order.getCategoryDrug().getDrug().getPrice());
        orderDto.setQuantity(order.getCategoryDrug().getQuantity());
        orderDto.setMonney(order.getMonney());
        return orderDto;
    }

//    public Order toOrder(){
//        Order order = new Order();
//
//    }
}
