package com.flashforward.backendspring.service;

import com.flashforward.backendspring.dto.BasicShoeInfoDTO;
import com.flashforward.backendspring.model.Shoe;
import com.flashforward.backendspring.repository.ShoeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoeService {

    ShoeRepo shoeRepo;

    @Autowired
    public ShoeService(ShoeRepo shoeRepo) {
        this.shoeRepo = shoeRepo;
    }


    // Returns all shoes from repository converted into basicShoeInfoDTO class
    // within price range
    public List<BasicShoeInfoDTO> getAll(double minPrice, double maxPrice) {

        List<Shoe> allShoes = shoeRepo.findAllByPriceIsBetween(minPrice, maxPrice);

        return basicShoeInfoDTOConverter(allShoes);
    }


    // Returns all shoes from repository converted into basicShoeInfoDTO class
    // within price range,  by brand
    public List<BasicShoeInfoDTO> getAllShoesByBrand(String brand, double minprice, double maxPrice) {

        List<Shoe> shoes = shoeRepo.findAllByBrandAndPriceIsBetween(brand, minprice, maxPrice);

        return basicShoeInfoDTOConverter(shoes);
    }

    // Returns all shoes from repository converted into basicShoeInfoDTO class
    // within price range,  in ascending or descending price order
    public List<BasicShoeInfoDTO> getShoesByPriceOrder(double minPrice, double maxPrice, String order) {

        List<Shoe> shoes = new ArrayList<>();

        if (order.equals("asc")) {
            shoes = shoeRepo.findAllWithinPriceRange(minPrice, maxPrice, Sort.by("price").ascending());
        } else if (order.equals("desc")) {
            shoes = shoeRepo.findAllWithinPriceRange(minPrice, maxPrice, Sort.by("price").descending());
        } // todo : add else for wrong price-order value

        return basicShoeInfoDTOConverter(shoes);
    }


    // RETURNS ALL SHOES FROM REPO CONVERTED TO BASIC SHOE INFO DTO CLASS BY BRAND
    // BY PRICE IN ASCENDING OR DESCENDING ORDER
    public List<BasicShoeInfoDTO> getAllShoesByBrandOrderedByPrice(String brand, String order) {
// todo rename allshoes
        List<Shoe> allShoes;

        if (order.equals("asc")) {
            allShoes = shoeRepo.findAllByBrand(brand, Sort.by("price").ascending());
        } else {
            allShoes = shoeRepo.findAllByBrand(brand, Sort.by("price").descending());
        }

        return basicShoeInfoDTOConverter(allShoes);
    }

    // RETURNS ALL SHOES FROM REPO CONVERTED TO BASIC SHOE INFO DTO CLASS
    // WITHIN GIVEN PRICE RANGE
    public List<BasicShoeInfoDTO> getAllShoesWithinPriceRange(double minPrice, double maxPrice) {
        List<Shoe> shoes = shoeRepo.findAllWithinPriceRange(minPrice, maxPrice);
        return basicShoeInfoDTOConverter(shoes);
    }


    // CONVERTS LIST OF SHOE CLASS TO LIST OF BASIC_SHOE_INFO_DTO CLASS
    private List<BasicShoeInfoDTO> basicShoeInfoDTOConverter(List<Shoe> shoes) {

        return shoes.stream()
                .map(s -> new BasicShoeInfoDTO(s.getId(), s.getName(), s.getBrand(), s.getPrice()))
                .toList();
    }

}
