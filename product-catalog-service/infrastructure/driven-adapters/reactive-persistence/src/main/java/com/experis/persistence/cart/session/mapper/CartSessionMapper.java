package com.experis.persistence.cart.session.mapper;


import com.experis.model.cart.session.CartSessionModel;
import com.experis.persistence.cart.session.model.CartSession;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CartSessionMapper {

  CartSessionModel toModel(CartSession cartSession);

}
