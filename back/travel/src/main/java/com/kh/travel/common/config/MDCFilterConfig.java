package com.kh.travel.common.config;

import com.kh.travel.common.filter.MDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MDCFilterConfig {

    @Bean
    public FilterRegistrationBean<MDCFilter> MDCFilter(){
        FilterRegistrationBean<MDCFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new MDCFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    } // MDCFilter
} // class
