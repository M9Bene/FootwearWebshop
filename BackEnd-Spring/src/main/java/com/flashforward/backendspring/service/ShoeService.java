package com.flashforward.backendspring.service;


import com.flashforward.backendspring.dto.BasicShoeInfoDTO;
import com.flashforward.backendspring.model.Shoe;
import com.flashforward.backendspring.repository.ShoeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ShoeService {

    ShoeRepo shoeRepo;

    @Autowired
    public ShoeService(ShoeRepo shoeRepo) {
        this.shoeRepo = shoeRepo;
    }



    // CONVERTS SHOE CLASS TO BASIC SHOE INFO DTO CLASS
    private BasicShoeInfoDTO basicShoeInfoDTOConverter(Shoe shoe) {

        return new BasicShoeInfoDTO(shoe.getId(), shoe.getName(), shoe.getBrand(), shoe.getPrice());
    }
}
