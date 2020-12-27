package com.example.nk.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product {
  private Long id;
  private String name;
  private Integer productNumber;
  private String imagePath;
  private Date entryDate;
  private Date exitDate;

  public Product() {
  }

  public Product(String name, Integer productNumber, String imagePath) {
    this.name = name;
    this.productNumber = productNumber;
    this.imagePath = imagePath;
  }

  @Id
  @Column(name="id", unique=true)
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  @Column(name = "product_name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  @Column(name = "product_number")
  public Integer getProductNumber() {
    return productNumber;
  }

  public void setProductNumber(Integer productNumber) {
    this.productNumber = productNumber;
  }
  @Column(name = "image_path")
  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
  @Column(name = "entry_date")
  public Date getEntryDate() {
    return entryDate;
  }

  public void setEntryDate(Date entryDate) {
    this.entryDate = entryDate;
  }
  @Column(name = "exit_date")
  public Date getExitDate() {
    return exitDate;
  }

  public void setExitDate(Date exitDate) {
    this.exitDate = exitDate;
  }
}
