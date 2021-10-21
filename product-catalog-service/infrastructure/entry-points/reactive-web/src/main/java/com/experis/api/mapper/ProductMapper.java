package com.experis.api.mapper;

import com.experis.api.request.FiltersDTO;
import com.experis.model.product.ProductQueryFilters;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {

  ProductQueryFilters toProductQueryFilters(FiltersDTO filtersDTO);

}
