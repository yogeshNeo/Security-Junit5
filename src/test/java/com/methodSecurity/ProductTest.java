package com.methodSecurity;

import com.methodSecurity.config.UserInfoUserDetailsService;
import com.methodSecurity.controller.ProductController;
import com.methodSecurity.repository.ProductRepository;
import com.methodSecurity.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProductController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ProductTest {

    private static final String END_POINT_PATH = "/products/";

    ProductController product;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepo;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserInfoUserDetailsService userDetailsService;

    @BeforeEach
    void setUp() {
        product = new ProductController();
    }

    @Test
    @DisplayName("Test case for fetching all products")
    void fetchAllProducts() throws Exception {

        System.out.println("Test case for fetching all products");

        mockMvc.perform(get(END_POINT_PATH + "/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

}
