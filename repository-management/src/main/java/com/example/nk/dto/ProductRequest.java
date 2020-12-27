package com.example.nk.dto;

import com.example.nk.entities.Product;
import org.springframework.web.multipart.MultipartFile;

public class ProductRequest {
  private Product product;
  private MultipartFile multipartFile;

  public ProductRequest() {
  }

  public ProductRequest(Product product, MultipartFile multipartFile) {
    this.product = product;
    this.multipartFile = multipartFile;
  }

  public ProductRequest(Product product) {
    this.product = product;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public MultipartFile getMultipartFile() {
    return multipartFile;
  }

  public void setMultipartFile(MultipartFile multipartFile) {
    this.multipartFile = multipartFile;
  }
}
