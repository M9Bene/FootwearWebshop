package com.flashforward.backendspring.repository;

import com.flashforward.backendspring.model.Shoe;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ShoeRepo extends JpaRepository<Shoe, Integer> {

    @Query("SELECT s FROM Shoe s WHERE s.price BETWEEN :minPrice AND :maxPrice")
    List<Shoe> findAllWithinPriceRange(double minPrice, double maxPrice, Sort sort);

    List<Shoe> findAllByBrandAndPriceIsBetween(String brand, double minPrice, double maxPrice);

    List<Shoe> findAllByPriceIsBetween(double minPrice, double maxPrice);

    List<Shoe> findAllByBrandAndPriceIsBetween(String brand, double minPrice, double maxPrice, Sort sort);


    @Query("SELECT sh FROM Shoe sh INNER JOIN SizeAndQuantity sq ON sh.id = sq.shoe.id " +
            "WHERE sq.size = :size AND sh.price BETWEEN :minPrice AND :maxPrice")
    List<Shoe> findAllBySize(double minPrice, double maxPrice, int size);


    @Query("SELECT sh FROM Shoe sh INNER JOIN SizeAndQuantity sq ON sh.id = sq.shoe.id " +
            "WHERE sq.size = :size AND sh.price BETWEEN :minPrice AND :maxPrice")
    List<Shoe> findAllBySizeInPriceOrder(double minPrice, double maxPrice, int size, Sort sort);


    @Query("SELECT sh FROM Shoe sh INNER JOIN SizeAndQuantity sq ON sh.id = sq.shoe.id " +
            "WHERE sq.size = :size AND sh.brand = :brand AND sh.price BETWEEN :minPrice AND :maxPrice")
    List<Shoe> findAllBySizeAndBrand(double minPrice, double maxPrice, int size, String brand);


    @Query("SELECT sh FROM Shoe sh INNER JOIN SizeAndQuantity sq ON sh.id = sq.shoe.id " +
            "WHERE sq.size = :size AND sh.brand = :brand AND sh.price BETWEEN :minPrice AND :maxPrice")
    List<Shoe> findAllBySizeAndBrandInPriceOrder(double minPrice, double maxPrice, int size, String brand, Sort sort);


    Shoe findById(int id);
}
