package com.flashforward.backendspring.repository;

import com.flashforward.backendspring.model.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoeRepo extends JpaRepository <Shoe, Integer>{

        List<Shoe> findAll();
}
