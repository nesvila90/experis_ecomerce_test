package com.experis.model.commons.message;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PRIVATE)
public enum BusinessErrorMessage {

  INVALID_STATUS("BU_0001", "Status not founded."),
  PRODUCT_NOT_FOUNDED("PB_0001", "Product not founded."),
  MISSING_REQUIRED_FIELD("PB_0002", "Parameters not found"),
  NOT_ENOUGH_STOCK("PB_0003", "Stock no available.");

  private final String code;
  private final String message;

}
