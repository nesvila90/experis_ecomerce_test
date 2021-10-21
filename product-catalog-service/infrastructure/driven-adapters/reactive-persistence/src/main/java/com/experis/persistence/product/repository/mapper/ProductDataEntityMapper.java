package com.experis.persistence.product.repository.mapper;

import com.experis.model.commons.PageDTO;
import com.experis.model.commons.PageMetadata;
import com.experis.model.product.ProductModel;
import com.experis.persistence.product.model.Product;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductDataEntityMapper {

  private final ProductEntityMapper productEntityMapper;

  public PageDTO<ProductModel> toPageDTO(Page<Product> page) {

    PageMetadata pageMetadata = PageMetadata.builder()
        .number(page.getNumber())
        .totalElements(page.getTotalElements())
        .totalPages(page.getTotalPages())
        .size(page.getSize())
        .build();

    List<ProductModel> productModels = page.getContent().stream().map(productEntityMapper::toModel).collect(Collectors.toList());
    return PageDTO.<ProductModel>builder()
        .pageMetadata(pageMetadata)
        .content(productModels)
        .build();
  }

}
