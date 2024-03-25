package com.codegym.casemodule5.controller;

import com.codegym.casemodule5.dto.DrugDto;
import com.codegym.casemodule5.service.IDrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/drugs")

public class DrugController {
    @Value("${upload}")
    private String upload;

    @Autowired
    private IDrugService iDrugService;

    @GetMapping()
    public ResponseEntity<List<DrugDto>> getAll(){
        return new ResponseEntity<>(iDrugService.findAll() ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugDto> get(@PathVariable Long id){
        DrugDto drug = iDrugService.findById(id);
        if(drug != null) {
            return new ResponseEntity<>(drug, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findByCategory")
    public ResponseEntity<List<DrugDto>> findByCategory(@RequestParam("id") Long id){
        return new ResponseEntity<>(iDrugService.findByCategory(id) ,HttpStatus.OK);
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<DrugDto>> findByName(@RequestParam String name){
        iDrugService.findByName(name);
        return new ResponseEntity<>(iDrugService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/findByPrice")
    public ResponseEntity<List<DrugDto>> findByPrice(@RequestParam int price1, @RequestParam int price2){
        return new ResponseEntity<>(iDrugService.findByPrice(price1, price2) ,HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<DrugDto> create(@ModelAttribute DrugDto drugDto){
        return new ResponseEntity<>(iDrugService.add(drugDto), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<DrugDto> update(@RequestBody DrugDto drugDto){
        DrugDto drug1 = iDrugService.update(drugDto);
        if(drug1 == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(drug1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        if(iDrugService.delete(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/sortASC")
    public ResponseEntity<List<DrugDto>> findAllOrderByPriceASC(){
        Sort sort = Sort.by(Sort.Direction.ASC, "price");
        return new ResponseEntity<>(iDrugService.findAllOrderByPrice(sort) ,HttpStatus.OK);
    }
    @GetMapping("/sortDESC")
    public ResponseEntity<List<DrugDto>> findAllOrderByPriceDESC(){
        Sort sort = Sort.by(Sort.Direction.DESC, "price");
        return new ResponseEntity<>(iDrugService.findAllOrderByPrice(sort) ,HttpStatus.OK);
    }
}
