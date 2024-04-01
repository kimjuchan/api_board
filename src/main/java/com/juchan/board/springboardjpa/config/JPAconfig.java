package com.juchan.board.springboardjpa.config;


import com.juchan.board.springboardjpa.api.aop.CreateDataList;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;


//BaseEntity Auditing 설정관련 어노테이션
@EnableJpaAuditing
@Configuration
public class JPAconfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.ofNullable(SecurityContextHolder.getContext())
                .map(SecurityContext::getAuthentication)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getName);
    }
    //Page index 값이 0 -> 1부터 시작하게 설정.
   /* @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize() {
        return p -> p.setOneIndexedParameters(true);
    }
    */
}
