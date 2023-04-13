package com.flashforward.backendspring.repository;

import com.flashforward.backendspring.model.Shoe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class ShoeRepoTest {

    @Autowired
    ShoeRepo shoeRepo;


    // Convention for method naming =    MethodName_StateUnderTest_ExpectedBehavior

    // Test for findById
    @Test
    public void findById_id2_exists() {

        assertNotNull(shoeRepo.findById(2));
    }

    // Test for findById
    @Test
    public void findById_id4_isNull() {

        Shoe testShoe2 = shoeRepo.findById(4);

        assertNull(testShoe2);
    }

    // Test for findById
    @Test
    public void findById_id3_hasCorrectName() {

        Shoe testShoe3 = shoeRepo.findById(3);

        assertEquals(testShoe3.getName(), "Test Shoe 3");
    }

    // Test for findAllWithinPriceRange
    @Test
    public void findAllWithinPriceRange_tooHighRange_isEmpty() {

        List<Shoe> shoes = shoeRepo.findAllWithinPriceRange(20000000, 21000000,
                Sort.by("price").ascending());

        assertTrue(shoes.isEmpty());
    }

    // Test for findAllWithinPriceRange
    @Test
    public void findAllWithinPriceRange_wideRange_hasSize3() {

        List<Shoe> shoes = shoeRepo.findAllWithinPriceRange(0, 21000000,
                Sort.by("price").ascending());

        assertEquals(shoes.size(), 3);
    }

    // Test for findAllWithinPriceRange
    @Test
    public void findAllWithinPriceRange_wideRangeAscendingOrder_firstElementIsCheaperThanOthers() {

        List<Shoe> shoes = shoeRepo.findAllWithinPriceRange(0, 21000000,
                Sort.by("price").ascending());

        assertTrue(shoes.get(0).getPrice() < shoes.get(1).getPrice()
                && shoes.get(0).getPrice() < shoes.get(2).getPrice());
    }

    // Test for findAllByBrandAndPriceIsBetween
    @Test
    public void findAllByBrandAndPriceIsBetween_adidasTooHighRange_isEmpty() {

        List<Shoe> shoes = shoeRepo.findAllByBrandAndPriceIsBetween("adidas",
                20000000, 21000000);

        assertTrue(shoes.isEmpty());
    }

    // Test for findAllByBrandAndPriceIsBetween
    @Test
    public void findAllByBrandAndPriceIsBetween_adidasWideRange_hasSize1() {

        List<Shoe> shoes = shoeRepo.findAllByBrandAndPriceIsBetween("adidas",
                0, 21000000);

        assertEquals(shoes.size(), 1);
    }

    // Test for findAllByPriceIsBetween
    @Test
    public void findAllByPriceIsBetween_wideRange_hasSize3() {

        List<Shoe> shoes = shoeRepo.findAllByPriceIsBetween(0, 2000000);

        assertEquals(shoes.size(), 3);
    }

    // Test for findAllByPriceIsBetween
    @Test
    public void findAllByPriceIsBetween_tooHighRange_isEmpty() {

        List<Shoe> shoes = shoeRepo.findAllByPriceIsBetween(2000000, 2100000);

        assertTrue(shoes.isEmpty());
    }

    // Test for findAllByBrandAndPriceIsBetween with sort
    @Test
    public void findAllByBrandAndPriceIsBetween_tooHighRange_isEmpty() {

        List<Shoe> shoes = shoeRepo.findAllByBrandAndPriceIsBetween("adidas", 2000000, 2100000);

        assertTrue(shoes.isEmpty());
    }

    // Test for findAllByBrandAndPriceIsBetween with sort
    @Test
    public void findAllByBrandAndPriceIsBetween_nikeWideRange_hasSize2() {

        List<Shoe> shoes = shoeRepo.findAllByBrandAndPriceIsBetween("nike", 0, 2100000);

        assertEquals(shoes.size(), 2);
    }

    // Test for findAllBySize
    @Test
    public void findAllBySize_size42_hasSize2() {

        List<Shoe> shoes = shoeRepo.findAllBySize(0, 200000, 41);

        assertEquals(shoes.size(), 2);
    }

    // Test for findAllBySize
    @Test
    public void findAllBySize_size43_hasSize1() {

        List<Shoe> shoes = shoeRepo.findAllBySize(0, 200000, 43);

        assertEquals(shoes.size(), 1);
    }

    // Test for findAllBySize
    @Test
    public void findAllBySize_size48_isEmpty() {

        List<Shoe> shoes = shoeRepo.findAllBySize(0, 200000, 48);

        assertTrue(shoes.isEmpty());
    }

    // Test for findAllBySizeInPriceOrder
    @Test
    public void findAllBySizeInPriceOrder_size41Ascending_firstElementHasCorrectName() {

        List<Shoe> shoes = shoeRepo.findAllBySizeInPriceOrder(0, 200000, 41,
                Sort.by("price").ascending());

        assertEquals(shoes.get(0).getName(), "Test Shoe 2");
    }

    // Test for findAllBySizeInPriceOrder
    @Test
    public void findAllBySizeInPriceOrder_size41Descending_firstElementHasCorrectName() {

        List<Shoe> shoes = shoeRepo.findAllBySizeInPriceOrder(0, 200000, 41,
                Sort.by("price").descending());

        assertEquals(shoes.get(0).getName(), "Test Shoe 1");
    }

    // Test for findAllBySizeAndBrand
    @Test
    public void findAllBySizeAndBrand_adidasSize40_isEmpty() {

        List<Shoe> shoes = shoeRepo.findAllBySizeAndBrand(0, 2000000, 40, "adidas");

        assertTrue(shoes.isEmpty());
    }

    // Test for findAllBySizeAndBrand
    @Test
    public void findAllBySizeAndBrand_nikeSize41_hasSize2() {

        List<Shoe> shoes = shoeRepo.findAllBySizeAndBrand(0, 2000000, 41, "nike");

        assertEquals(shoes.size(), 2);
    }

    // Test for findAllBySizeAndBrandInPriceOrder
    @Test
    public void findAllBySizeAndBrandInPriceOrder_adidasSize38_isEmpty() {

        List<Shoe> shoes = shoeRepo.findAllBySizeAndBrand(0, 2000000, 38, "adidas");

        assertTrue(shoes.isEmpty());
    }

    // Test for findAllBySizeAndBrandInPriceOrder
    @Test
    public void findAllBySizeAndBrandInPriceOrder_nike41Ascending_firstElementHasCorrectName() {

        List<Shoe> shoes = shoeRepo.findAllBySizeAndBrandInPriceOrder(0, 2000000, 41,
                "nike", Sort.by("price").ascending());

        assertEquals(shoes.get(0).getName(), "Test Shoe 2");
    }

    // Test for findAllBySizeAndBrandInPriceOrder
    @Test
    public void findAllBySizeAndBrandInPriceOrder_nike41Descending_firstElementHasCorrectName() {

        List<Shoe> shoes = shoeRepo.findAllBySizeAndBrandInPriceOrder(0, 2000000, 41,
                "nike", Sort.by("price").ascending());

        assertEquals(shoes.get(0).getName(), "Test Shoe 2");
    }

}
