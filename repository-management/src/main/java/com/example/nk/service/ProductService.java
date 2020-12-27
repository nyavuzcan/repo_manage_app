package com.example.nk.service;

import com.example.nk.entities.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
  ResponseEntity<Product> addProduct(Product product);
  ResponseEntity<Product> editProduct(Product product);
  ResponseEntity<Product> deleteProduct(Long id);
  ResponseEntity<List<Product>> getAllProducts();
  ResponseEntity<Product> getOneProduct(Long id);
  ResponseEntity<List<Product>> getSearchedProducts(String product);
}
