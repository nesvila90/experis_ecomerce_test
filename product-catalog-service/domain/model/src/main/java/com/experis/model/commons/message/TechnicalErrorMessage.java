package com.experis.model.commons.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TechnicalErrorMessage {

  UNEXPECTED_EXCEPTION("CMT0006", "Error inesperado");

  private final String code;
  private final String message;
}
