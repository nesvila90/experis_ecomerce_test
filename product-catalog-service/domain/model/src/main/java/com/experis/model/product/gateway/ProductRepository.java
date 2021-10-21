package com.experis.model.product.gateway;

import com.experis.model.commons.PageDTO;
import com.experis.model.product.ProductModel;
import com.experis.model.product.ProductQueryFilters;
import com.experis.model.inventory.InventoryModel;
import reactor.core.publisher.Mono;

public interface ProductRepository {

  Mono<PageDTO<ProductModel>> findByFilters(ProductQueryFilters queryFilters);

  Mono<InventoryModel> getStock(Long productId);
}
