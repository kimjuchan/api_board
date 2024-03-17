package com.juchan.board.springboardjpa.config;


import com.juchan.board.springboardjpa.api.aop.CreateDataList;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;


//BaseEntity Auditing 설정관련 어노테이션
@EnableJpaAuditing
@Configuration
public class JPAconfig {

    @Bean
    public AuditorAware<String> auditorAware(){
        return () -> Optional.of("JCkim"); // TODO : 추후 User 생성 후 로그인 정보 담으면 된다.
    }

}
