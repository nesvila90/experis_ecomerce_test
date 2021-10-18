package com.experis.batch;

import com.experis.model.product.Product;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class ProductProcessor implements ItemProcessor<Product, Product> {

  @Override
  public Product process(Product item) throws Exception {
    //Passtru for item, it hasn't transformations
    return item;
  }
}
