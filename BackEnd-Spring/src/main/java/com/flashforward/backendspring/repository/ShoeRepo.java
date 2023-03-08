package com.flashforward.backendspring.repository;

import com.flashforward.backendspring.model.Shoe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoeRepo extends JpaRepository <Shoe, Integer>{

        List<Shoe> findAll();

        List<Shoe> findAll(Sort sort);

        List<Shoe> findAllByBrand(String brand);

        List<Shoe> findAllByBrand(String brand, Sort sort);

        @Query("SELECT s FROM Shoe s WHERE s.price BETWEEN :minPrice AND :maxPrice")
        List<Shoe> findAllWithinPriceRange(double minPrice, double maxPrice);
}
