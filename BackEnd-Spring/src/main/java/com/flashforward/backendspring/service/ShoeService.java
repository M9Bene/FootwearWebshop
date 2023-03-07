package com.flashforward.backendspring.service;

import com.flashforward.backendspring.dto.BasicShoeInfoDTO;
import com.flashforward.backendspring.model.Shoe;
import com.flashforward.backendspring.repository.ShoeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

        return allShoes.stream()
                .map(this::basicShoeInfoDTOConverter)
                .collect(Collectors.toList());
    }


    // CONVERTS SHOE CLASS TO BASIC SHOE INFO DTO CLASS
    private BasicShoeInfoDTO basicShoeInfoDTOConverter(Shoe shoe) {

        return new BasicShoeInfoDTO(shoe.getId(), shoe.getName(), shoe.getBrand(), shoe.getPrice());
    }
}
