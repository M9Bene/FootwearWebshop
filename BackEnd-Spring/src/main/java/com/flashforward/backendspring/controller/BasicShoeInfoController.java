package com.flashforward.backendspring.controller;


import com.flashforward.backendspring.dto.BasicShoeInfoDTO;
import com.flashforward.backendspring.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    // RETURNS ALL SHOE'S BASIC SHOE INFO DTO IN ASCENDING OR DESCENDING PRICE ORDER
    @GetMapping("/all/price-order/{order}")
    public List<BasicShoeInfoDTO> getAllShoesOrderedByPrice(@PathVariable(name = "order") String order) {
        return shoeService.getAllShoesOrderedByPrice(order);
    }

    // RETURNS ALL SHOE'S BASIC SHOE INFO DTO BY BRAND
    @GetMapping("/all/by-brand/{brand}")
    public List<BasicShoeInfoDTO> getAllShoesByBrand(@PathVariable(name = "brand") String brand) {
        return shoeService.getAllShoesByBrand(brand);
    }

    // RETURNS ALL SHOE'S BASIC SHOE INFO DTO BY BRAND IN ASCENDING OR DESCENDING PRICE ORDER
    @GetMapping("/by-brand/{brand}/price-order/{order}")
    public List<BasicShoeInfoDTO> getAllShoesByBrandOrderedByPrice(@PathVariable(name = "brand") String brand,
                                                                   @PathVariable(name = "order") String order) {
        return shoeService.getAllShoesByBrandOrderedByPrice(brand, order);
    }

    // RETURNS ALL SHOE'S BASIC SHOE INFO DTO WITHIN GIVEN PRICE RANGE
    @GetMapping("/within-price/min/{min}/max/{max}")
    public List<BasicShoeInfoDTO> getAllShoesWithinPriceRange(@PathVariable(name = "min") double minPrice,
                                                              @PathVariable(name = "max") double maxprice) {
        return shoeService.getAllShoesWithinPriceRange(minPrice, maxprice);
    }

    // RETURNS ALL SHOE'S BASIC SHOE INFO DTO WITHIN GIVEN PRICE RANGE IN ASCENDING OR DESCENDING PRICE ORDER
    @GetMapping("/within-price/min/{min}/max/{max}/price-order/{order}")
    public List<BasicShoeInfoDTO> getAllShoesWithinPriceRangeOrderedByPrice(@PathVariable(name = "min") double minPrice,
                                                                            @PathVariable(name = "max") double maxPrice,
                                                                            @PathVariable(name = "order") String order)
    {
        return shoeService.getAllShoesWithinPriceRangeOrderedByPrice(minPrice, maxPrice, order);
    }

    // RETURNS ALL SHOE'S BASIC SHOE INFO DTO BY BRAND AND WITHIN GIVEN PRICE RANGE
    @GetMapping("/by-brand/{brand}/within-price/min/{min}/max/{max}")
    public List<BasicShoeInfoDTO> getAllShoesByBrandWithinPriceRange(@PathVariable(name = "brand") String brand,
                                                                     @PathVariable(name= "min") double minPrice,
                                                                     @PathVariable(name = "max") double maxPrice){
        return shoeService.getAllShoesByBrandWithinPriceRange(brand, minPrice, maxPrice);
    }

}
