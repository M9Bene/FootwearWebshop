package com.flashforward.backendspring.controller;


import com.flashforward.backendspring.dto.BasicShoeInfoDTO;
import com.flashforward.backendspring.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/basic-shoe-info")
public class BasicShoeInfoController {

    private final ShoeService shoeService;


    @Autowired
    public BasicShoeInfoController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }


    // RETURNS ALL SHOE'S BASIC SHOE INFO DTO
    @GetMapping("/all")
    public List<BasicShoeInfoDTO> getAllShoes() {
        return shoeService.getAllBasicShoeInfo();
    }

    @GetMapping("/all-ordered-by-price-asc")
    public List<BasicShoeInfoDTO> getAllShoesByAscPrice(){
        return shoeService.getAllShoesByAscPrice();
    }

    @GetMapping("/all-ordered-by-price-desc")
    public List<BasicShoeInfoDTO> getAllShoesByDescPrice(){
        return shoeService.getAllShoesByDescPrice();
    }
}
