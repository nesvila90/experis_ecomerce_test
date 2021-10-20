package com.experis.usecase.catalog;

import com.experis.model.commons.BusinessException;
import com.experis.model.commons.PageDTO;
import com.experis.model.commons.message.BusinessErrorMessage;
import com.experis.model.inventory.InventoryModel;
import com.experis.model.product.ProductModel;
import com.experis.model.product.ProductQueryFilters;
import com.experis.model.product.gateway.ProductRepository;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class ProductCatalogUseCase {

  private final ProductRepository productRepository;

  public Mono<PageDTO<ProductModel>> findByFilters(ProductQueryFilters filters) {
    return productRepository.findByFilters(filters)
        .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.PRODUCT_NOT_FOUNDED)));
  }

  public Mono<InventoryModel> verifyStock(Long productId) {
    return productRepository.getStock(productId)
        .flatMap(validateStock())
        .switchIfEmpty(Mono.error(new BusinessException(BusinessErrorMessage.PRODUCT_NOT_FOUNDED)));
  }

  private Function<InventoryModel, Mono<? extends InventoryModel>> validateStock() {
    return inventoryModel -> inventoryModel.getQuantity() <= 0 ?
        Mono.error(new BusinessException(BusinessErrorMessage.NOT_ENOUGH_STOCK)) :
        Mono.just(inventoryModel);
  }


}
