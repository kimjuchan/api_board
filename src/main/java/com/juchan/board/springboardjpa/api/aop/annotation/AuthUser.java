package com.juchan.board.springboardjpa.api.aop.annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
//#this == 현재 인증된 principal 객체 값
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : memberPrincipalDetails")
public @interface AuthUser {

}
