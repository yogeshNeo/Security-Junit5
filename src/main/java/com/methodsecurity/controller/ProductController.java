package com.methodsecurity.controller;

import com.methodsecurity.entity.Product;
import com.methodsecurity.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;

@RestController
@RequestMapping("/products/")
public class ProductController {

    @Autowired
    private ProductRepository productRepo;

    @GetMapping("all")
    @Secured("ADMIN")
    // @RolesAllowed({"ADMIN"})
   // @PreFilter("hasRole('USER')")
    public List<Product> getAllTheProducts() {
        return productRepo.findAll();
    }

    /**
     * Get a product by its id
     * @param id
     * @return
     */
    @Operation(summary = "Get a product by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the product",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "product not found",
                    content = @Content)})
    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Product getProductById(@PathVariable int id) {
        return productRepo.findById(id).get();
    }

    @PostMapping("new")
    @PostAuthorize("hasAuthority('ADMIN')")
    public Product saveProduct(@RequestBody Product product) {
        return productRepo.save(product);
    }

    @DeleteMapping("delete/{id}")
    @Secured("ADMIN")
    public void deleteProduct(@PathVariable int id) {
         productRepo.deleteById(id);
    }

}
