package com.example.demo.service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository userRepository;

    public Optional<Product> findById(Long id) {
        return userRepository.findById(id);
    }

    public boolean existById(Long id) {
        return userRepository.existsById(id);
    }

    public List<Product> findAll() {
        return userRepository.findAll();
    }
}
