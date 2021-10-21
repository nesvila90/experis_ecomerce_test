package com.experis.model.cart.session.gateway;

import com.experis.model.cart.session.CartSessionModel;
import reactor.core.publisher.Mono;

public interface CartSessionRepository {

  Mono<CartSessionModel> findById(Long cartId);

  Mono<CartSessionModel> createCartSession();

  Mono<Integer> updateCartSession(String user, Double total, Long cartId);
}
