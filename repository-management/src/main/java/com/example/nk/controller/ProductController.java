package com.example.nk.controller;

import com.example.nk.entities.Product;
import com.example.nk.repository.ProductRepository;
import com.example.nk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {

  @Autowired
  ProductService productService;

  @Autowired
  ProductRepository productRepository;

  @RequestMapping(value = "/getProducts", method = RequestMethod.GET)
  @Cacheable(value = "cacheGetProducts")
  public ResponseEntity<List<Product>> addProduct() {
    return productService.getAllProducts();
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return productService.addProduct(product);
  }

  @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<Product> editProduct(@RequestBody Product product) {
    return productService.editProduct(product);
  }

  @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
  ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
    return productService.deleteProduct(id);
  }

  @RequestMapping(value = "/getProduct/{id}", method = RequestMethod.GET)
  ResponseEntity<Product> getProduct(@PathVariable Long id) {
    return productService.getOneProduct(id);
  }

  @RequestMapping(value = "/search/{name}", method = RequestMethod.GET)
  ResponseEntity<List<Product>> editProduct(@PathVariable String name) {
    return productService.getSearchedProducts(name);
  }
}
