package com.flashforward.backendspring.controller;

import com.flashforward.backendspring.dto.BasicShoeInfoDTO;
import com.flashforward.backendspring.service.ShoeService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class BasicShoeInfoControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Mock
    private ShoeService shoeService;

    @InjectMocks
    private BasicShoeInfoController basicShoeInfoController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(basicShoeInfoController).build();
    }

    // data for testing
    private final BasicShoeInfoDTO basicShoeInfoDTO1 = new BasicShoeInfoDTO(1, "Test Shoe 1", "nike", 80.0,
            "img_url");
    private final BasicShoeInfoDTO basicShoeInfoDTO2 = new BasicShoeInfoDTO(2, "Test Shoe 2", "nike", 100.0,
            "img_url");
    private final BasicShoeInfoDTO basicShoeInfoDTO3 = new BasicShoeInfoDTO(3, "Test Shoe 3", "adidas", 100.0,
            "img_url");
    private final BasicShoeInfoDTO basicShoeInfoDTO4 = new BasicShoeInfoDTO(4, "Test Shoe 4", "adidas", 120.0,
            "img_url");

    // base url
    private final String baseUrl = "http://localhost:8080/api/basic-shoe-info/p-range";


    // test for getAllShoes
    @Test
    public void testGetAllShoes() throws Exception {
        List<BasicShoeInfoDTO> basicShoeInfoDTOS = new ArrayList<>(Arrays.asList(basicShoeInfoDTO1, basicShoeInfoDTO2,
                basicShoeInfoDTO3, basicShoeInfoDTO4));

        when(shoeService.getAll(0.0, 150.0)).thenReturn(basicShoeInfoDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUrl + "/0.0/150.0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Test Shoe 1")));
    }

    // test for getShoesByBrand
    @Test
    public void testGetShoesByBrand() throws Exception {
        List<BasicShoeInfoDTO> basicShoeInfoDTOS = new ArrayList<>(Arrays.asList(basicShoeInfoDTO1, basicShoeInfoDTO2));

        when(shoeService.getAllShoesByBrand("nike", 0.0, 120.0))
                .thenReturn(basicShoeInfoDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUrl + "/0.0/120.0/by/nike")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Test Shoe 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].brand", Matchers.is("nike")));
    }

    // test for getShoesOrderedByPrice
    @Test
    public void testGetShoesOrderedByPrice() throws Exception {
        List<BasicShoeInfoDTO> basicShoeInfoDTOS = new ArrayList<>(Arrays.asList(basicShoeInfoDTO1, basicShoeInfoDTO2,
                basicShoeInfoDTO4));

        when(shoeService.getShoesByPriceOrder(0.0, 120.0, "asc"))
                .thenReturn(basicShoeInfoDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUrl + "/0.0/120.0/p-order/asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Test Shoe 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price", Matchers.is(100.0)));
    }

    // test for getShoesByBrandAndPriceOrder
    @Test
    public void testGetShoesByBrandAndPriceOrder() throws Exception {
        List<BasicShoeInfoDTO> basicShoeInfoDTOS = new ArrayList<>(Arrays.asList(basicShoeInfoDTO4, basicShoeInfoDTO3));

        when(shoeService.getShoesByBrandAndPriceOrder(0.0, 120.0, "adidas", "desc"))
                .thenReturn(basicShoeInfoDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUrl + "/0.0/120.0/by/adidas/p-order/desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Test Shoe 4")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(120.0)));
    }


    // test for getShoesBySize
    @Test
    public void testGetShoesBySize() throws Exception {
        List<BasicShoeInfoDTO> basicShoeInfoDTOS = new ArrayList<>(Arrays.asList(basicShoeInfoDTO1, basicShoeInfoDTO2));

        when(shoeService.getShoesBySize(0.0, 120.0, 42))
                .thenReturn(basicShoeInfoDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUrl + "/0.0/120.0/by-size/42")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", Matchers.is("Test Shoe 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price", Matchers.is(80.0)));
    }

    // test for getShoesBySizeAndPriceOrder
    @Test
    public void testGetShoesBySizeAndPriceOrder() throws Exception {
        List<BasicShoeInfoDTO> basicShoeInfoDTOS = new ArrayList<>(Arrays.asList(basicShoeInfoDTO1, basicShoeInfoDTO2));

        when(shoeService.getShoesBySizeAndPriceOrder(0.0, 120.0, 42, "asc"))
                .thenReturn(basicShoeInfoDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUrl + "/0.0/120.0/by-size/42/p-order/asc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Test Shoe 2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price", Matchers.is(100.0)));
    }

    // test for getShoesBySizeAndBrand
    @Test
    public void testGetShoesBySizeAndBrand() throws Exception {
        List<BasicShoeInfoDTO> basicShoeInfoDTOS = new ArrayList<>(Arrays.asList(basicShoeInfoDTO3, basicShoeInfoDTO4));

        when(shoeService.getShoesBySizeAndBrand(0.0, 120.0, 45, "adidas"))
                .thenReturn(basicShoeInfoDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUrl + "/0.0/120.0/by-size/45/by/adidas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Test Shoe 4")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].brand", Matchers.is("adidas")));
    }

    // test for getShoesBySizeAndBrandAndPriceOrder
    @Test
    public void testGetShoesBySizeAndBrandAndPriceOrder() throws Exception {
        List<BasicShoeInfoDTO> basicShoeInfoDTOS = new ArrayList<>(Arrays.asList(basicShoeInfoDTO4, basicShoeInfoDTO3));

        when(shoeService.getShoesBySizeAndBrandAndPriceOrder(0.0, 120.0, 42, "adidas",
                "desc"))
                .thenReturn(basicShoeInfoDTOS);

        mockMvc.perform(MockMvcRequestBuilders
                        .get(baseUrl + "/0.0/120.0/by-size/42/by/adidas/p-order/desc")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", Matchers.is("Test Shoe 3")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].brand", Matchers.is("adidas")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price", Matchers.is(100.0)));
    }
}
