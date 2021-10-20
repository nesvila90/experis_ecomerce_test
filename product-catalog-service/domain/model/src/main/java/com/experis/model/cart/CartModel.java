package com.experis.model.cart;

import com.experis.model.inventory.InventoryModel;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class CartModel {

  private Long cartId;
  private List<InventoryModel> products;
  private boolean sold;

}
