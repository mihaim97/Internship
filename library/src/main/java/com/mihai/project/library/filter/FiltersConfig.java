package com.mihai.project.library.filter;

import com.mihai.project.library.filter.filters.AdminSecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean<AdminSecurityFilter> securityFilter(){
        FilterRegistrationBean<AdminSecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminSecurityFilter());
        registrationBean.addUrlPatterns("/user/*");
        return registrationBean;
    }
}
