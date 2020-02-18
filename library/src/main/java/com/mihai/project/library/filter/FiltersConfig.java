package com.mihai.project.library.filter;

import com.mihai.project.library.filter.filters.SecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean<SecurityFilter> securityFilter(){
        FilterRegistrationBean<SecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SecurityFilter());
        registrationBean.addUrlPatterns("/test/*");
        return registrationBean;
    }
}
