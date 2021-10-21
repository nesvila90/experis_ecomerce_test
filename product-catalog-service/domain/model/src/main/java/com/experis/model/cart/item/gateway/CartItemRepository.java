package com.experis.model.cart.item.gateway;

import com.experis.model.cart.item.CartModel;
import com.experis.model.cart.item.EmptyCart;
import com.experis.model.inventory.InventoryModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CartItemRepository {

  Mono<CartModel> addProduct(Long cartId, InventoryModel product, Integer quantity);

  Flux<CartModel> getCartProducts(Long cartId);

  Mono<EmptyCart> removeProducts(Long cartId);


}
