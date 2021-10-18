package com.experis.model.product.gateway;

import com.experis.model.product.Product;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository {

  Flux<Product> findAll();

  Flux<Product> findByName(String name);

  Flux<Product> findByPriceRange(Double initialPrice, Double finalPrice);

  Flux<Product> findByBrand(String brand);

  Flux<Product> saveAll();

  Mono<Product> save(Product product);

  Mono<Product> update(Product product);

  Mono<Boolean> deleteByName(String name);

}
