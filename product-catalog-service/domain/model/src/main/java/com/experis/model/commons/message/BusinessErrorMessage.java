package com.experis.model.commons.message;

import static lombok.AccessLevel.PRIVATE;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = PRIVATE)
public enum BusinessErrorMessage {

  INVALID_STATUS("BU_0001", "Status not founded.");

  private final String code;
  private final String description;

}
