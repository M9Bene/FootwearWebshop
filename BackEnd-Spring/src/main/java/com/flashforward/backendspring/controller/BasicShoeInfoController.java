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


    // Returns all shoe's basicShoeInfoDTO within price range
    @GetMapping("/all/p-range/{min}/{max}")
    public List<BasicShoeInfoDTO> getAllShoes(@PathVariable(name = "min") double minPrice,
                                              @PathVariable(name = "max") double maxPrice) {
        return shoeService.getAll(minPrice, maxPrice);
    }



    // Returns all shoe's basicShoeInfoDTO within price range and by brand
    @GetMapping("/all/p-range/{min}/{max}/by/{brand}")
    public List<BasicShoeInfoDTO> getAllShoesByBrand(@PathVariable(name = "brand") String brand,
                                                     @PathVariable(name = "min") double minprice,
                                                     @PathVariable(name = "max") double maxPrice) {
        return shoeService.getAllShoesByBrand(brand, minprice, maxPrice);
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
                                                                            @PathVariable(name = "order") String order) {
        return shoeService.getAllShoesWithinPriceRangeOrderedByPrice(minPrice, maxPrice, order);
    }
}
