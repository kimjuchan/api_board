package com.juchan.board.springboardjpa.config;


import com.juchan.board.springboardjpa.api.aop.CreateDataList;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;

import java.util.Optional;


//BaseEntity Auditing 설정관련 어노테이션
@EnableJpaAuditing
@Configuration
public class JPAconfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        //실행 순서 test
        System.out.println("##JPAconfig setting....");
        return () -> Optional.of("JCkim"); // TODO : 추후 User 생성 후 로그인 정보 담으면 된다.
    }


    //Page index 값이 0 -> 1부터 시작하게 설정.
   /* @Bean
    public PageableHandlerMethodArgumentResolverCustomizer customize() {
        return p -> p.setOneIndexedParameters(true);
    }
    */
}
