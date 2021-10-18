package com.experis.model.commons;

import com.experis.model.commons.message.TechnicalErrorMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TechnicalException extends Exception {

  private final TechnicalErrorMessage technicalErrorMessage;

}
