package com.experis.api.util;

import com.experis.api.commons.CartProperties;
import com.experis.api.commons.ProductCatalogProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
    ProductCatalogProperties.class,
    CartProperties.class,
})
public class RoutesProperties {

}
