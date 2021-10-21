package com.experis.api;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import com.experis.api.commons.ProductCatalogProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class ProductCatalogRouter {

  private final ProductCatalogProperties productCatalogProperties;

  @Bean
  public RouterFunction<ServerResponse> routerProductCatalog(ProductCatalogHandler productCatalogHandler) {
    return route()
        .GET(productCatalogProperties.getRoot(), productCatalogHandler::finder)
        .build();
  }

}
