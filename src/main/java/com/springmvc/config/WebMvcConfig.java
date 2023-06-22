package com.springmvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * @author BAO
 * 6/21/2023
 */

/**
 * Spring framework supports two ways of creating RESTful service: using MVC with ModelAndView, using HTTP message converters.
 * <p>
 * The ModelAndView approach is older, nhưng phức tạp và đòi hỏi cấu hình nhiều hơn. Nó cố gắng áp dụng mô hình REST vào old model, phát sinh 1 số vấn đề. -> Spring 3.0 ra đời cung cấp REST cấp cao hơn.
 * <p>
 * Spring đã cung cấp phiên bảng spring 3.9. GIảm thiểu sự phức tạp và đău ra cách tiếp cận dễ dàng hơn để xây dựng và phục vụ API RESTful.
 * <p>
 * The new approach, based on HttpMessageConverter and annotations, is much more lightweigh(nhe nhang`)  and easy to implement. Configuration is minimal, and it provides sensible defaults for what we would expect from a RESTful service.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.springmvc"})
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }


    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MappingJackson2HttpMessageConverter());
    }
}
