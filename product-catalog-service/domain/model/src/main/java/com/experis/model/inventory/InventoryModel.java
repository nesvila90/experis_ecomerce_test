package com.experis.model.inventory;

import com.experis.model.product.ProductModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class InventoryModel {

  private Long productId;
  private Integer quantity;
  private ProductModel product;

}
