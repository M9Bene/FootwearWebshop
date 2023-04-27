package com.flashforward.backendspring.controller;


import com.flashforward.backendspring.dto.DetailedShoeInfoDTO;
import com.flashforward.backendspring.service.ShoeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/detailed-shoe-info")
public class DetailedShoeInfoController {

    private final ShoeService shoeService;

    @Autowired
    public DetailedShoeInfoController(ShoeService shoeService) {
        this.shoeService = shoeService;
    }

    @GetMapping("/shoe-id/{id}")
    public DetailedShoeInfoDTO getDetailedShoeInfoById(@PathVariable(name = "id") int id){

        return shoeService.getDetailedShoeInfoById(id);
    }
}
