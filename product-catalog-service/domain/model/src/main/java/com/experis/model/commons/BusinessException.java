package com.experis.model.commons;

import com.experis.model.commons.message.BusinessErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BusinessException extends Exception {

  private final BusinessErrorMessage businessErrorMessage;

}
