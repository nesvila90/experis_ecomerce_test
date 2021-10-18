package com.experis.batch;

import com.experis.h2.config.H2Config;
import com.experis.model.product.Product;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableBatchProcessing
@RequiredArgsConstructor
public class ProductJobConfig {

  private static final String[] FIELDS_NAMES = FieldName.toStringArray();

  private static final String DELIMITER = ";";

  private final JobBuilderFactory jobBuilderFactory;

  private final StepBuilderFactory stepBuilderFactory;

  private H2Config h2Config;


  @Bean
  public FlatFileItemReader<Product> reader() {
    return new FlatFileItemReaderBuilder<Product>()
        .name("productItemReader")
        .resource(new ClassPathResource("sample-data.csv"))
        .delimited()
        .names(FIELDS_NAMES)
        .lineMapper(lineMapper())
        .fieldSetMapper(fieldSet -> getMapper(Product.class, fieldSet))
        .build();
  }

  @SneakyThrows
  private Product getMapper(Class type, FieldSet fieldSet) {
    var beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<Product>();
    beanWrapperFieldSetMapper.setTargetType(type);
    return beanWrapperFieldSetMapper.mapFieldSet(fieldSet);
  }

  @Bean
  public LineMapper<Product> lineMapper() {
    final DefaultLineMapper<Product> defaultLineMapper = new DefaultLineMapper<>();
    final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(DELIMITER);
    lineTokenizer.setStrict(true);
    lineTokenizer.setNames(FIELDS_NAMES);
    final ProductFieldSetMapper fieldSetMapper = new ProductFieldSetMapper();
    defaultLineMapper.setLineTokenizer(lineTokenizer);
    defaultLineMapper.setFieldSetMapper(fieldSetMapper);
    return defaultLineMapper;
  }

  @Bean
  public ProductProcessor processor() {
    return new ProductProcessor();
  }

  @Bean
  public JdbcBatchItemWriter<Product> writer() {
    var dataSource = h2Config.createDataSourceWithConfigurationProperties();
    return new JdbcBatchItemWriterBuilder<Product>()
        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
        .sql("INSERT INTO Product (name, brand, price, stockQuantity, status, discountPercentage) VALUES (:name, :brand, :price, :stockQuantity, :status, :discountPercentage)")
        .dataSource(dataSource)
        .build();
  }

  @Bean
  public Step step1(JdbcBatchItemWriter<Product> writer) {
    return stepBuilderFactory.get("step1")
        .<Product, Product> chunk(10)
        .reader(reader())
        .processor(processor())
        .writer(writer)
        .build();
  }

}
