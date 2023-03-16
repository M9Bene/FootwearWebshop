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
    public List<BasicShoeInfoDTO> getShoesByBrand(@PathVariable(name = "brand") String brand,
                                                  @PathVariable(name = "min") double minprice,
                                                  @PathVariable(name = "max") double maxPrice) {
        return shoeService.getAllShoesByBrand(brand, minprice, maxPrice);
    }


    // Returns all shoe's basicShoeInfoDTO within price range in ascending or descending price order
    @GetMapping("/p-range/{min}/{max}/p-order/{order}")
    public List<BasicShoeInfoDTO> getShoesOrderedByPrice(@PathVariable(name = "min") double minPrice,
                                                         @PathVariable(name = "max") double maxPrice,
                                                         @PathVariable(name = "order") String order) {
        return shoeService.getShoesByPriceOrder(minPrice, maxPrice, order);
    }


    // Returns all shoe's basicShoeInfoDTO within price range in ascending or descending price order and by brand
    @GetMapping("/p-range/{min}/{max}/by/{brand}/p-order/{order}")
    public List<BasicShoeInfoDTO> getShoesBybrandAndPriceOrder(@PathVariable(name = "min") double minPrice,
                                                               @PathVariable(name = "max") double maxPrice,
                                                               @PathVariable(name = "brand") String brand,
                                                               @PathVariable(name = "order") String order) {

        return shoeService.getShoesbyBrandAndPriceorder(minPrice, maxPrice, brand, order);
    }


    // Returns all shoe's basicShoeInfoDTO within price range by size
    @GetMapping("/p-range/{min}/{max}/by-size/{size}")
    public List<BasicShoeInfoDTO> getShoesBySize(@PathVariable(name = "min") double minPrice,
                                                 @PathVariable(name = "max") double maxPrice,
                                                 @PathVariable(name = "size") int size) {

        return shoeService.getShoesBySize(minPrice, maxPrice, size);
    }


    // Returns all shoe's basicShoeInfoDTO within price range by size and price order
    @GetMapping("/p-range/{min}/{max}/by-size/{size}/p-order/{order}")
    public List<BasicShoeInfoDTO> getShoesBySizeAndPriceOrder(@PathVariable(name = "min") double minPrice,
                                                              @PathVariable(name = "max") double maxPrice,
                                                              @PathVariable(name = "size") int size,
                                                              @PathVariable(name = "order") String order) {

        return shoeService.getShoesBySizeAndPriceOrder(minPrice, maxPrice, size, order);
    }


    // Returns all shoe's basicShoeInfoDTO within price range by size and brand
    @GetMapping("/p-range/{min}/{max}/by-size/{size}/by/{brand}")
    public List<BasicShoeInfoDTO> getShoesBySizeAndBrand(@PathVariable(name = "min") double minprice,
                                                         @PathVariable(name = "max") double maxprice,
                                                         @PathVariable(name = "size") int size,
                                                         @PathVariable(name = "brand") String brand) {

        return shoeService.getShoesBySizeAndBrand(minprice, maxprice, size, brand);
    }


    // Returns all shoe's basicShoeInfoDTO within price range by size and brand and price order
    @GetMapping("/p-range/{min}/{max}/by-size/{size}/by/{brand}/p-order/{order}")
    public List<BasicShoeInfoDTO> getShoesBySizeAndBrandAndPriceOrder(@PathVariable(name = "min") double minPrice,
                                                                      @PathVariable(name = "max") double maxPrice,
                                                                      @PathVariable(name = "size") int size,
                                                                      @PathVariable(name = "brand") String brand,
                                                                      @PathVariable(name = "order") String order){

        return shoeService.getShoesBySizeAndBrandAndPriceOrder(minPrice, maxPrice, size, brand, order);
    }
}
