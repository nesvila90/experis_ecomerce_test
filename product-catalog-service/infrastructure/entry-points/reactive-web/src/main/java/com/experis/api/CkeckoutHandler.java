package com.experis.api;

import com.experis.api.request.ProductCartDTO;
import com.experis.api.util.ResponseUtil;
import com.experis.model.commons.TechnicalException;
import com.experis.model.commons.message.TechnicalErrorMessage;
import com.experis.usecase.cart.CartUseCase;
import com.experis.usecase.checkout.CheckoutUseCase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CkeckoutHandler {

  private final CheckoutUseCase checkoutUseCase;

  private final ResponseUtil responseUtil;

  public Mono<ServerResponse> checkout(ServerRequest serverRequest) {
    Long cartId = Long.valueOf(serverRequest.queryParam("cartId").get());
    return checkoutUseCase.buy(cartId)
        .collectList()
        .flatMap(cartModels ->  responseUtil.buildCartContentResponse(cartModels));
  }



}
