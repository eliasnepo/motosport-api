package com.eliasnepo.motosport.infraestructure.category;

import com.eliasnepo.motosport.application.category.create.dto.CreateCategoryRequest;
import com.eliasnepo.motosport.application.category.create.dto.CreateCategoryResponse;
import com.eliasnepo.motosport.infraestructure.category.jpa.CategoryRepositoryJpa;
import com.eliasnepo.motosport.infraestructure.config.security.TestSecurityConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = TestSecurityConfig.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc(printOnlyOnFailure = false)
public class CategoryControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CategoryRepositoryJpa repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    @DisplayName("should create a category when valid params")
    void test1() throws Exception {
        CreateCategoryRequest requestContent = new CreateCategoryRequest("Formula 1");
        String payload = objectMapper.writeValueAsString(requestContent);

        MockHttpServletRequestBuilder request = post("/categories/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(payload);

        String response = mockMvc.perform(request)
                .andExpect(
                        MockMvcResultMatchers.status().isCreated()
                )
                .andExpect(
                        MockMvcResultMatchers.redirectedUrlPattern("http://localhost/categories/*")
                )
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

        CreateCategoryResponse categoryResponse = objectMapper.readValue(response, CreateCategoryResponse.class);

        assertEquals(1, repository.findAll().size());
        assertNotNull(categoryResponse);
        assertEquals(requestContent.getName(), categoryResponse.getName());
    }
}
