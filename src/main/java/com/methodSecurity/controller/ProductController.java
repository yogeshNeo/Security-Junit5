package com.methodSecurity.controller;

import com.methodSecurity.entity.Product;
import com.methodSecurity.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
