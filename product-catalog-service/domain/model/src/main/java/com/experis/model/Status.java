package com.experis.model;

import com.experis.model.commons.BusinessException;
import com.experis.model.commons.message.BusinessErrorMessage;
import java.util.Arrays;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum Status {

  NEW("Nuevo"),
  USED("Usado");

  private final String descriptionName;

  @SneakyThrows
  public static Status setStatusFromString(String value){
    return Arrays.stream(Status.values()).filter(status -> status.getDescriptionName().equalsIgnoreCase(value)).findAny()
        .orElseThrow(() -> new BusinessException(BusinessErrorMessage.INVALID_STATUS));
  }
}
