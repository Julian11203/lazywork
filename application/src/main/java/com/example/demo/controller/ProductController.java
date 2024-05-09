package com.example.demo.controller;

import com.example.demo.service.ProductService;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> findAll(){
        if(productService.findAll().isEmpty()){
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.ok(productService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findById(@PathVariable Long id){
        if(productService.existById(id)){
            return ResponseEntity.ok().body(productService.findById(id));
        }
        return ResponseEntity.notFound().build();
    }
}
