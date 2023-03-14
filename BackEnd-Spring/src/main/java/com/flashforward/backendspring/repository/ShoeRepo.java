package com.flashforward.backendspring.repository;

import com.flashforward.backendspring.model.Shoe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoeRepo extends JpaRepository <Shoe, Integer>{

        @Query("SELECT s FROM Shoe s WHERE s.price BETWEEN :minPrice AND :maxPrice")
        List<Shoe> findAllWithinPriceRange(double minPrice, double maxPrice, Sort sort);

        List<Shoe> findAllByBrandAndPriceIsBetween(String brand, double minPrice, double maxPrice);

        List<Shoe> findAllByPriceIsBetween(double minPrice, double maxPrice);

        List<Shoe> findAllByBrandAndPriceIsBetween(String brand, double minPrice, double maxPrice, Sort sort);
}
