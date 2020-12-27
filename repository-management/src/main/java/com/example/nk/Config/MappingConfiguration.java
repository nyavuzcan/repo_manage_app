package com.example.nk.Config;

import com.github.dozermapper.core.DozerBeanMapper;
import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Configuration
public class MappingConfiguration {

  @Bean
  public Mapper mapper() throws Exception {
    Mapper mapper = DozerBeanMapperBuilder.buildDefault();
    return mapper;
  }
}