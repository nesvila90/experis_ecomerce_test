package com.experis.model.cart.item;

import com.experis.model.inventory.InventoryModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CartItemModel {

  private Long cartId;
  private InventoryModel product;
}
