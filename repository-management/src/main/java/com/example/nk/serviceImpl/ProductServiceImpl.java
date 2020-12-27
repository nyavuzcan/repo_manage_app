package com.example.nk.serviceImpl;

import com.example.nk.entities.Product;
import com.example.nk.repository.ProductRepository;
import com.example.nk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  ProductRepository productRepository;
  @Override
  public ResponseEntity<Product> addProduct(Product product) {
    Date date = new Date();
    product.setEntryDate(date);
    Product productAdded = productRepository.save(product);
    if (Objects.nonNull(productAdded)) return new  ResponseEntity<Product>(HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
  }

  @Override
  public ResponseEntity<Product> editProduct(Product product) {
    Product savedProduct = productRepository.save(product);
    if (Objects.nonNull(savedProduct)) return new  ResponseEntity<Product>(HttpStatus.OK);
    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
  }

  @Override
  public ResponseEntity<Product> deleteProduct(Long id) {
   productRepository.deleteById(id);
  return new  ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Product>> getAllProducts() {
    return new ResponseEntity<List<Product>>(productRepository.findAll(),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Product> getOneProduct(Long id) {
    return new ResponseEntity<Product>(productRepository.findById(id).get(),HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<Product>> getSearchedProducts(String product) {
    return new ResponseEntity<List<Product>>(productRepository.searchByProductName(product),HttpStatus.OK);
  }
}
