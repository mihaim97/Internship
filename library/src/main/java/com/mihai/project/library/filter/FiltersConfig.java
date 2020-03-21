package com.mihai.project.library.filter;

import com.mihai.project.library.filter.filters.AdminSecurityFilter;
import com.mihai.project.library.filter.filters.RegularSecurityFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean<AdminSecurityFilter> securityFilter() {
        FilterRegistrationBean<AdminSecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AdminSecurityFilter());
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.addUrlPatterns("/book/*");
        registrationBean.addUrlPatterns("/rent-book/mark-late");
        registrationBean.addUrlPatterns("/rent-book/list");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<RegularSecurityFilter> regularSecurityFilter() {
        FilterRegistrationBean<RegularSecurityFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RegularSecurityFilter());
        registrationBean.addUrlPatterns("/rent-book/register");
        registrationBean.addUrlPatterns("/rent-book/return");
        registrationBean.addUrlPatterns("/rent-book/extend-rent");
        return  registrationBean;
    }
}
