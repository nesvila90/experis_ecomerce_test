package com.experis.batch;

import static lombok.AccessLevel.PRIVATE;

import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PRIVATE)
public enum FieldName {

  NAME("nombre"),
  BRAND("marca"),
  PRICE("precio"),
  STOCK_QUANTITY("cantidad en stock"),
  STATUS("estado"),
  DISCOUNT_PERCENTAGE("porcentaje descuento");

  private final String fieldName;

  public static final String[] toStringArray(){
    return Arrays.stream(FieldName.values()).toArray(String[]::new);
  }


}
