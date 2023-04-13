package com.flashforward.backendspring.service;

import com.flashforward.backendspring.dto.BasicShoeInfoDTO;
import com.flashforward.backendspring.dto.DetailedShoeInfoDTO;
import com.flashforward.backendspring.model.Shoe;
import com.flashforward.backendspring.model.SizeAndQuantity;
import com.flashforward.backendspring.repository.ShoeRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShoeServiceTest {

    @Mock
    private ShoeRepo shoeRepo;

    @InjectMocks
    private ShoeService shoeService;

    // datas for testing
    private final SizeAndQuantity sizeAndQuantityFor41 = new SizeAndQuantity(41, 1);
    private final SizeAndQuantity sizeAndQuantityFor42 = new SizeAndQuantity(42, 3);
    private final SizeAndQuantity sizeAndQuantityFor43 = new SizeAndQuantity(43, 4);
    private final SizeAndQuantity sizeAndQuantityFor44 = new SizeAndQuantity(44, 5);


    // Test for getAll
    @Test
    public void testGetAll() {

        List<Shoe> shoes = Arrays.asList(
                new Shoe("Test Shoe 1", "nike", 90.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41, sizeAndQuantityFor42)),

                new Shoe("Test Shoe 2", "adidas", 100.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor43)),

                new Shoe("Test Shoe 3", "adidas", 110.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor44)));

        when(shoeRepo.findAllByPriceIsBetween(50.0, 120.0)).thenReturn(shoes);

        List<BasicShoeInfoDTO> basicShoeInfoDTOs = shoeService.getAll(50.0, 120.0);

        assertFalse(basicShoeInfoDTOs.isEmpty());
        assertEquals(3, basicShoeInfoDTOs.size());
        assertEquals("Test Shoe 2", basicShoeInfoDTOs.get(1).name());
    }

    // Test for getAllShoesByBrand
    @Test
    public void testGetAllShoesByBrand() {

        List<Shoe> shoes = List.of(
                new Shoe("Test Shoe 1", "nike", 90.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41, sizeAndQuantityFor42)));

        when(shoeRepo.findAllByBrandAndPriceIsBetween("nike", 50.0, 120.0)).thenReturn(shoes);

        List<BasicShoeInfoDTO> basicShoeInfoDTOs = shoeService.getAllShoesByBrand("nike",
                50.0, 120.0);

        assertFalse(basicShoeInfoDTOs.isEmpty());
        assertEquals(1, basicShoeInfoDTOs.size());
        assertEquals("Test Shoe 1", basicShoeInfoDTOs.get(0).name());
    }

    // Test for getShoesByPriceOrder
    @Test
    public void testGetShoesByPriceOrder() {

        List<Shoe> shoes = List.of(
                new Shoe("Test Shoe 1", "nike", 90.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41, sizeAndQuantityFor42)),
                new Shoe("Test Shoe 2", "adidas", 100.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor43)));

        when(shoeRepo.findAllWithinPriceRange(50.0, 105.0, Sort.by("price").ascending()))
                .thenReturn(shoes);

        List<BasicShoeInfoDTO> basicShoeInfoDTOs = shoeService.getShoesByPriceOrder(
                50.0, 105.0, "asc");

        assertFalse(basicShoeInfoDTOs.isEmpty());
        assertEquals(2, basicShoeInfoDTOs.size());
        assertTrue(basicShoeInfoDTOs.get(0).price() < basicShoeInfoDTOs.get(1).price());
        assertEquals("Test Shoe 2", basicShoeInfoDTOs.get(1).name());
    }

    // Test for getShoesByBrandAndPriceOrder
    @Test
    public void testGetShoesByBrandAndPriceOrder() {
        List<Shoe> shoes = List.of(
                new Shoe("Test Shoe 1", "nike", 100.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41, sizeAndQuantityFor42)),
                new Shoe("Test Shoe 2", "nike", 95.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor43)));

        when(shoeRepo.findAllByBrandAndPriceIsBetween("nike", 50.0, 105.0,
                Sort.by("price").descending()))
                .thenReturn(shoes);

        List<BasicShoeInfoDTO> basicShoeInfoDTOs = shoeService.getShoesByBrandAndPriceOrder(
                50.0, 105.0, "nike", "desc");

        assertFalse(basicShoeInfoDTOs.isEmpty());
        assertEquals(2, basicShoeInfoDTOs.size());
        assertTrue(basicShoeInfoDTOs.get(1).price() < basicShoeInfoDTOs.get(0).price());
        assertEquals("Test Shoe 2", basicShoeInfoDTOs.get(1).name());
    }

    // Test for getShoesBySize
    @Test
    public void testGetShoesBySize() {
        List<Shoe> shoes = List.of(
                new Shoe("Test Shoe 1", "nike", 100.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41, sizeAndQuantityFor42)),
                new Shoe("Test Shoe 2", "adidas", 80.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41)));

        when(shoeRepo.findAllBySize(0.0, 200.0, 41))
                .thenReturn(shoes);

        List<BasicShoeInfoDTO> basicShoeInfoDTOs = shoeService.getShoesBySize(0.0, 200.0, 41);

        assertFalse(basicShoeInfoDTOs.isEmpty());
        assertEquals(2, basicShoeInfoDTOs.size());
        assertEquals("Test Shoe 2", basicShoeInfoDTOs.get(1).name());
    }

    // Test for getShoesBySizeAndPriceOrder
    @Test
    public void testGetShoesBySizeAndPriceOrder() {
        List<Shoe> shoes = List.of(
                new Shoe("Test Shoe 1", "nike", 100.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41, sizeAndQuantityFor42)),
                new Shoe("Test Shoe 2", "adidas", 80.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41)));

        when(shoeRepo.findAllBySizeInPriceOrder(0.0, 120.0, 41,
                Sort.by("price").descending()))
                .thenReturn(shoes);


        List<BasicShoeInfoDTO> basicShoeInfoDTOs = shoeService.getShoesBySizeAndPriceOrder(0.0, 120.0,
                41, "desc");

        assertFalse(basicShoeInfoDTOs.isEmpty());
        assertEquals(2, basicShoeInfoDTOs.size());
        assertTrue(basicShoeInfoDTOs.get(0).price() > basicShoeInfoDTOs.get(1).price());
        assertEquals("Test Shoe 2", basicShoeInfoDTOs.get(1).name());
    }


    // Test for getShoesBySizeAndBrand
    @Test
    public void testGetShoesBySizeAndBrand() {
        List<Shoe> shoes = List.of(
                new Shoe("Test Shoe 1", "nike", 100.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41, sizeAndQuantityFor42)));

        when(shoeRepo.findAllBySizeAndBrand(0.0, 200.0, 42, "nike"))
                .thenReturn(shoes);


        List<BasicShoeInfoDTO> basicShoeInfoDTOs = shoeService.getShoesBySizeAndBrand(0.0, 200.0,
                42, "nike");

        assertFalse(basicShoeInfoDTOs.isEmpty());
        assertEquals(1, basicShoeInfoDTOs.size());
        assertEquals("nike", basicShoeInfoDTOs.get(0).brand());
    }

    // Test for getShoesBySizeAndBrandAndPriceOrder
    @Test
    public void testGetShoesBySizeAndBrandAndPriceOrder() {
        List<Shoe> shoes = List.of(
                new Shoe("Test Shoe 1", "nike", 100.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41)),
                new Shoe("Test Shoe 2", "nike", 102.0, "img_url",
                        "detailed_info", List.of(sizeAndQuantityFor41, sizeAndQuantityFor42)));

        when(shoeRepo.findAllBySizeAndBrandInPriceOrder(0.0, 110.0, 41, "nike",
                Sort.by("price").ascending()))
                .thenReturn(shoes);

        List<BasicShoeInfoDTO> basicShoeInfoDTOs = shoeService.getShoesBySizeAndBrandAndPriceOrder(
                0.0, 110.0, 41, "nike", "asc");

        assertFalse(basicShoeInfoDTOs.isEmpty());
        assertEquals(2, basicShoeInfoDTOs.size());
        assertEquals("nike", basicShoeInfoDTOs.get(0).brand());
        assertTrue(basicShoeInfoDTOs.get(0).price() < basicShoeInfoDTOs.get(1).price());
    }

    // Test for getDetailedShoeInfoById
    @Test
    public void testGetDetailedShoeInfoById() {

        Shoe shoe = new Shoe("Test Shoe 1", "nike", 120.0, "img_url", "detailed info",
                List.of(sizeAndQuantityFor42, sizeAndQuantityFor43, sizeAndQuantityFor44));

        when(shoeRepo.findById(1)).thenReturn(shoe);

        DetailedShoeInfoDTO detailedShoeInfoDTO = shoeService.getDetailedShoeInfoById(1);

        assertNotNull(detailedShoeInfoDTO);
        assertEquals("Test Shoe 1", detailedShoeInfoDTO.name());
        assertEquals("nike", detailedShoeInfoDTO.brand());
        assertEquals(120.0, detailedShoeInfoDTO.price());
        assertEquals("detailed info", detailedShoeInfoDTO.detailedInfo());
        assertEquals(3, detailedShoeInfoDTO.sizeAndQuantityList().size());
        assertEquals(3, (detailedShoeInfoDTO.sizeAndQuantityList().get(0).getQuantity()));
    }
}
