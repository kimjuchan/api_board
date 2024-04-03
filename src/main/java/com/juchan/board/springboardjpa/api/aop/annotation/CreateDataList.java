package com.juchan.board.springboardjpa.api.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * Custom Annotation 주의 사항.
 * 반복적인 코드를 커스텀 어노테이션으로 처리하게 되면서 비지니스 로직에 더 집중 가능.
 * 반대로 간결하지만 숨은 의도를 파악하기가 조금 힘듬 ( 편하다고 막 사용하면 나중에 타인이 바라볼때 조금 복잡해 보일 수 있다??..)
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreateDataList {
    //custom annotation을 통해서 init data setting 작업.
}
