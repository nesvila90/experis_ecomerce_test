package com.experis.api.util;

import com.experis.model.cart.item.CartModel;
import com.experis.model.cart.item.EmptyCart;
import com.experis.model.commons.PageDTO;
import com.experis.model.product.ProductModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ResponseUtil {

  public Mono<ServerResponse> buildResponse(CartModel messageResponse) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(messageResponse);
  }

  public Mono<ServerResponse> buildResponse(PageDTO<ProductModel> messageResponse) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(messageResponse);
  }

  public Mono<ServerResponse> buildCartContentResponse(List<CartModel> messageResponse) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(messageResponse);
  }

  public Mono<ServerResponse> buildCartContentResponse(EmptyCart messageResponse) {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(messageResponse);
  }
}
