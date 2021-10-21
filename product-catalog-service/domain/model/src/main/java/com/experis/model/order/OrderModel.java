package com.experis.model.order;

import com.experis.model.cart.item.CartModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class OrderModel {

  private Long id;
  private CartModel productsPurchased;
  private Double totalPrice;


}
