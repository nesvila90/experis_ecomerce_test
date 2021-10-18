package com.experis.usecase.batch.product;
import com.experis.model.product.Product;
import com.experis.model.product.gateway.ProductRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ProductBatchUseCase {

  private final ProductRepository productRepository;

//  public Flux<Product> massiveLoad(String )

}
