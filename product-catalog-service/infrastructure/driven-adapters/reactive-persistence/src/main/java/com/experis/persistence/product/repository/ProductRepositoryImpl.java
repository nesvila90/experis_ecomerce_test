package com.experis.persistence.product.repository;

import com.experis.model.commons.PageDTO;
import com.experis.model.product.FieldName;
import com.experis.model.product.ProductModel;
import com.experis.model.product.ProductQueryFilters;
import com.experis.model.inventory.InventoryModel;
import com.experis.model.product.gateway.ProductRepository;
import com.experis.persistence.product.model.Product;
import com.experis.persistence.product.repository.mapper.ProductDataEntityMapper;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepository {

  private final R2dbcEntityTemplate r2dbcEntityTemplate;

  private final ProductDataEntityMapper productDataEntityMapper;

  @Override
  public Mono<PageDTO<ProductModel>> findByFilters(ProductQueryFilters queryFilters) {

    Criteria base = createFindByFiltersCriteria(queryFilters);

    var pageNumber = queryFilters.getPageNumber();
    var pageSize = queryFilters.getPageSize();

    var pagination = PageRequest.of(pageNumber, pageSize);

    Query findByFilters = Query.query(base).with(pagination);
    return r2dbcEntityTemplate.select(Product.class)
        .matching(findByFilters)
        .all().collectList()
        .zipWith(r2dbcEntityTemplate.count(findByFilters, Product.class))
        .map(product -> new PageImpl<>(product.getT1(), pagination, product.getT2()))
        .map(productDataEntityMapper::toPageDTO);
  }

  @Override
  public Mono<InventoryModel> getStock(Long productId) {
    Criteria verifyStockCriteria = buildVerifyStockCriteria(productId);
    Query isStockAvailableQuery = Query.query(verifyStockCriteria);
    return r2dbcEntityTemplate.select(Product.class)
        .matching(isStockAvailableQuery)
        .one().map(product -> InventoryModel.builder().productId(productId).quantity(product.getStockQuantity()).build());
  }


  private Criteria createFindByFiltersCriteria(ProductQueryFilters queryFilters) {
    var name = queryFilters.getName();
    var brand = queryFilters.getBrand();
    var initialPriceRange = queryFilters.getInitialPriceRange();
    var finalPriceRange = queryFilters.getFinalPriceRange();

    var isNameExist = validateStringEmptyAndNullValue(name);
    var isBrandExist = validateStringEmptyAndNullValue(brand);
    var isRangeExist = validateStringEmptyAndNullValue(initialPriceRange) || validateStringEmptyAndNullValue(finalPriceRange);

    var base = Criteria.empty();

    base = isNameExist ? base : base.and(FieldName.NAME.name()).is(name);
    base = isBrandExist ? base : base.and(FieldName.BRAND.name()).is(brand);
    base = isRangeExist ? base : base.and(FieldName.PRICE.name()).between(initialPriceRange, finalPriceRange);
    return base;
  }

  private Criteria buildVerifyStockCriteria(Long productId) {
    return Criteria.where(FieldName.ID.name()).is(productId)
        .and(FieldName.STOCK_QUANTITY.name())
        .greaterThan(0);
  }

  private boolean validateStringEmptyAndNullValue(String evaluateString) {
    return Objects.isNull(evaluateString) || evaluateString.isEmpty();
  }

}
