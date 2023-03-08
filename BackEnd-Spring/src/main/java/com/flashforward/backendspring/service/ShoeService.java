package com.flashforward.backendspring.service;

import com.flashforward.backendspring.dto.BasicShoeInfoDTO;
import com.flashforward.backendspring.model.Shoe;
import com.flashforward.backendspring.repository.ShoeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoeService {

    ShoeRepo shoeRepo;

    @Autowired
    public ShoeService(ShoeRepo shoeRepo) {
        this.shoeRepo = shoeRepo;
    }


    // RETURNS ALL SHOES FROM REPO CONVERTED TO BASIC SHOE INFO DTO CLASS
    public List<BasicShoeInfoDTO> getAllBasicShoeInfo() {

        List<Shoe> allShoes = shoeRepo.findAll();

        return basicShoeInfoDTOConverter(allShoes);
    }

    // RETURNS ALL SHOES FROM REPO CONVERTED TO BASIC SHOE INFO DTO CLASS
    // BY PRICE IN ASCENDING OR DESCENDING ORDER
    public List<BasicShoeInfoDTO> getAllShoesOrderedByPrice(String order) {
// todo rename allshoes
        List<Shoe> allShoes;

        if (order.equals("asc")) {
            allShoes = shoeRepo.findAll(Sort.by("price").ascending());
        } else {
            allShoes = shoeRepo.findAll(Sort.by("price").descending());
        }

        return basicShoeInfoDTOConverter(allShoes);
    }

    // RETURNS ALL SHOES FROM REPO CONVERTED TO BASIC SHOE INFO DTO CLASS BY BRAND
    public List<BasicShoeInfoDTO> getAllShoesByBrand(String brand) {
// todo rename allshoes
        List<Shoe> allShoes = shoeRepo.findAllByBrand(brand);

        return basicShoeInfoDTOConverter(allShoes);
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
        return  basicShoeInfoDTOConverter(shoes);
    }

    // RETURNS ALL SHOES FROM REPO CONVERTED TO BASIC SHOE INFO DTO CLASS
    // WITHIN GIVEN PRICE RANGE AND BY PRICE IN ASCENDING OR DESCENDING ORDER
    public List<BasicShoeInfoDTO> getAllShoesWithinPriceRangeOrderedByPrice(double minPrice, double maxPrice,
                                                                            String order) {
        List<Shoe> shoes;

        if (order.equals("asc")){
            shoes = shoeRepo.findAllWithinPriceRange(minPrice, maxPrice, Sort.by("price").ascending());
        }else {
            shoes = shoeRepo.findAllWithinPriceRange(minPrice, maxPrice, Sort.by("price").descending());
        }

        return  basicShoeInfoDTOConverter(shoes);
    }

    // CONVERTS LIST OF SHOE CLASS TO LIST OF BASIC_SHOE_INFO_DTO CLASS
    private List<BasicShoeInfoDTO> basicShoeInfoDTOConverter(List<Shoe> shoes) {

        return shoes.stream()
                .map(s -> new BasicShoeInfoDTO(s.getId(), s.getName(), s.getBrand(), s.getPrice()))
                .toList();
    }

}
