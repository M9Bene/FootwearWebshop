package com.flashforward.backendspring.controller;

import com.flashforward.backendspring.dto.DetailedShoeInfoDTO;
import com.flashforward.backendspring.model.SizeAndQuantity;
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

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class DetailedShoeInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    private ShoeService shoeService;

    @InjectMocks
    private DetailedShoeInfoController detailedShoeInfoController;

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(detailedShoeInfoController).build();
    }

    // test for getDetailedShoeInfoById
    @Test
    public void testGetDetailedShoeInfoById() throws Exception {
        DetailedShoeInfoDTO detailedShoeInfoDTO = new DetailedShoeInfoDTO(1, "Test Shoe 1", "nike",
                120.0, "img url", "detailed info",
                List.of(new SizeAndQuantity(41, 5), new SizeAndQuantity(42, 4)));

        when(shoeService.getDetailedShoeInfoById(1)).thenReturn(detailedShoeInfoDTO);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("http://localhost:8080/api/detailed-shoe-info/shoe-id/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.is("Test Shoe 1")))
                .andExpect(MockMvcResultMatchers.jsonPath("brand", Matchers.is("nike")))
                .andExpect(MockMvcResultMatchers.jsonPath("price", Matchers.is(120.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$..size", Matchers.is(List.of(41, 42))));
    }
}
