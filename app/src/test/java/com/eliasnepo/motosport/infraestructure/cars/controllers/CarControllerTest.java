package com.eliasnepo.motosport.infraestructure.cars.controllers;


import com.eliasnepo.motosport.application.cars.find.dto.FindCarResponse;
import com.eliasnepo.motosport.application.cars.list.dto.ListCarResponse;
import com.eliasnepo.motosport.application.exceptions.ResourceNotFoundException;
import com.eliasnepo.motosport.domain.cars.CarRepository;
import com.eliasnepo.motosport.factories.CarFactory;
import com.eliasnepo.motosport.factories.CategoryFactory;
import com.eliasnepo.motosport.infraestructure.cars.jpa.CarEntity;
import com.eliasnepo.motosport.infraestructure.cars.jpa.CarRepositoryJpa;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryEntity;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryRepositoryJpa;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class CarControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CarRepositoryJpa repository;

    @Autowired
    CategoryRepositoryJpa categoryRepository;


    @BeforeEach
    void setUp() {
        repository.deleteAll();
        categoryRepository.deleteAll();
    }

    @Test
    @DisplayName("should list a page of cars according to request params")
    void test1() throws Exception {
        int page = 0;
        int qtd = 2;

        CategoryEntity categoryEntity = CategoryFactory.create();
        categoryEntity = categoryRepository.save(categoryEntity);

        CarEntity carEntity = CarFactory.create(categoryEntity);
        CarEntity carEntity2 = CarFactory.create(categoryEntity);
        CarEntity carEntity3 = CarFactory.create(categoryEntity);
        List<CarEntity> carsList = List.of(carEntity, carEntity2, carEntity3);
        repository.saveAll(carsList);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/cars?page={page}&qtd={qtd}", page, qtd)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andExpect(jsonPath("$.content").exists())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].id").value(carEntity.getId()))
                .andExpect(jsonPath("$.content[1].id").value(carEntity2.getId()))
                .andExpect(jsonPath("$.size").value(qtd))
                .andExpect(jsonPath("$.number").value(page))
                .andExpect(jsonPath("$.totalElements").value(carsList.size()));;
    }

    @Test
    @DisplayName("should get info from a car when id exists")
    void test2() throws Exception {
        CategoryEntity categoryEntity = CategoryFactory.create();
        categoryEntity = categoryRepository.save(categoryEntity);
        CarEntity carEntity = CarFactory.create(categoryEntity);
        carEntity = repository.save(carEntity);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/cars/{id}", carEntity.getId())
                .contentType(MediaType.APPLICATION_JSON);

        String payload = mockMvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isOk()
                )
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        FindCarResponse response = objectMapper.readValue(payload, FindCarResponse.class);

        assertNotNull(response);
        assertEquals(carEntity.getName(), response.getName());
        assertEquals(carEntity.getId(), response.getId());
    }

    @Test
    @DisplayName("should not return a car when id doesn't exists")
    void test3() throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get("/cars/{id}", Integer.MAX_VALUE)
                .contentType(MediaType.APPLICATION_JSON);

        Exception resolvedException = mockMvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isNotFound()
                )
                .andReturn().getResolvedException();

        assertNotNull(resolvedException);
        assertEquals(ResourceNotFoundException.class, resolvedException.getClass());
    }
}
