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


    public List<BasicShoeInfoDTO> getAllShoesByAscPrice() {

        List<Shoe> allShoes = shoeRepo.findAll(Sort.by("price").ascending());

        return basicShoeInfoDTOConverter(allShoes);
    }

    // CONVERTS LIST OF SHOE CLASS TO LIST OF BASIC_SHOE_INFO_DTO CLASS
    private List<BasicShoeInfoDTO> basicShoeInfoDTOConverter(List<Shoe> shoes) {

        return shoes.stream()
                .map(s -> new BasicShoeInfoDTO(s.getId(), s.getName(), s.getBrand(), s.getPrice()))
                .toList();
    }


}
