package com.experis.model.product;

import com.experis.model.Status;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Product {

  private String id;
  private String name;
  private String brand;
  private Double price;
  private Integer stockQuantity;
  private Status status;
  private Double discountPercentage;

}
