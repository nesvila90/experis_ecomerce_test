package com.experis.batch;

import static com.experis.batch.FieldName.BRAND;
import static com.experis.batch.FieldName.DISCOUNT_PERCENTAGE;
import static com.experis.batch.FieldName.NAME;
import static com.experis.batch.FieldName.PRICE;
import static com.experis.batch.FieldName.STATUS;
import static com.experis.batch.FieldName.STOCK_QUANTITY;
import static com.experis.model.Status.setStatusFromString;

import com.experis.model.product.Product;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class ProductFieldSetMapper implements FieldSetMapper<Product> {

  @Override
  public Product mapFieldSet(FieldSet fieldSet) {
    return Product.builder()
        .name(fieldSet.readString(NAME.getFieldName()))
        .brand(fieldSet.readString(BRAND.getFieldName()))
        .price(fieldSet.readDouble(PRICE.getFieldName()))
        .stockQuantity(fieldSet.readInt(STOCK_QUANTITY.getFieldName()))
        .status(setStatusFromString(fieldSet.readString(STATUS.getFieldName())))
        .discountPercentage(fieldSet.readDouble(DISCOUNT_PERCENTAGE.getFieldName()))
        .build();
  }
}
