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


    // Returns all shoes from repository converted into basicShoeInfoDTO class
    // within price range, by brand,  in ascending or descending price order
    public List<BasicShoeInfoDTO> getShoesbyBrandAndPriceorder(double minPrice, double maxPrice, String brand,
                                                               String order) {
        List<Shoe> shoes = new ArrayList<>();

        if (order.equals("asc")) {
            shoes = shoeRepo.findAllByBrandAndPriceIsBetween(brand, minPrice, maxPrice,
                    Sort.by("price").ascending());
        } else if (order.equals("desc")) {
            shoes = shoeRepo.findAllByBrandAndPriceIsBetween(brand, minPrice, maxPrice,
                    Sort.by("price").descending());
        }// todo : add else for wrong price-order value

        return basicShoeInfoDTOConverter(shoes);
    }


    // Returns all shoes from repository converted into basicShoeInfoDTO class
    // within price range, by size
    public List<BasicShoeInfoDTO> getShoesBySize(double minPrice, double maxPrice, int size) {

        List<Shoe> shoes = shoeRepo.findAllBySize(minPrice, maxPrice, size);

        return basicShoeInfoDTOConverter(shoes);
    }


    // Returns all shoes from repository converted into basicShoeInfoDTO class
    // within price range, by size , by price order
    public List<BasicShoeInfoDTO> getShoesBySizeAndPriceOrder(double minPrice, double maxPrice, int size, String order) {

        List<Shoe> shoes = new ArrayList<>();

        if (order.equals("asc")) {
            shoes = shoeRepo.findAllBySizeInPriceOrder(minPrice, maxPrice, size, Sort.by("price").ascending());
        } else if (order.equals("desc")) {
            shoes = shoeRepo.findAllBySizeInPriceOrder(minPrice, maxPrice, size, Sort.by("price").descending());
        }// todo : add else for wrong price-order value

        return basicShoeInfoDTOConverter(shoes);
    }


    // Returns all shoes from repository converted into basicShoeInfoDTO class
    // within price range, by size and by brand
    public List<BasicShoeInfoDTO> getShoesBySizeAndBrand(double minprice, double maxprice, int size, String brand) {

        List<Shoe> shoes = shoeRepo.findAllBySizeAndBrand(minprice, maxprice, size, brand);

        return basicShoeInfoDTOConverter(shoes);
    }


    // Returns all shoes from repository converted into basicShoeInfoDTO class
    // within price range, by size and by brand and price order
    public List<BasicShoeInfoDTO> getShoesBySizeAndBrandAndPriceOrder(double minPrice, double maxPrice, int size,
                                                                      String brand, String order) {

        List<Shoe> shoes = new ArrayList<>();

        if (order.equals("asc")) {
            shoes = shoeRepo.findAllBySizeAndBrandInPriceOrder(minPrice, maxPrice, size, brand, Sort.by("price").ascending());
        } else if (order.equals("desc")) {
            shoes = shoeRepo.findAllBySizeAndBrandInPriceOrder(minPrice, maxPrice, size, brand, Sort.by("price").descending());
        }// todo : add else for wrong price-order value

        return basicShoeInfoDTOConverter(shoes);
    }

    // CONVERTS LIST OF SHOE CLASS TO LIST OF BASIC_SHOE_INFO_DTO CLASS
    private List<BasicShoeInfoDTO> basicShoeInfoDTOConverter(List<Shoe> shoes) {

        return shoes.stream()
                .map(s -> new BasicShoeInfoDTO(s.getId(), s.getName(), s.getBrand(), s.getPrice()))
                .toList();
    }
}
