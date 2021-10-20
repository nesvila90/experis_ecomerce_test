package com.experis.api;

import static com.experis.api.util.RequestUtil.extractFilters;

import com.experis.api.mapper.ProductMapper;
import com.experis.api.util.ResponseUtil;
import com.experis.usecase.catalog.ProductCatalogUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductCatalogHandler {

  private final ProductCatalogUseCase productCatalogUseCase;

  private final ProductMapper productMapper;

  private final ResponseUtil responseUtil;

  public Mono<ServerResponse> finder(ServerRequest serverRequest) {
    return extractFilters(serverRequest)
        .map(productMapper::toProductQueryFilters)
        .flatMap(productCatalogUseCase::findByFilters)
        .flatMap(responseUtil::buildResponse);
  }


}
