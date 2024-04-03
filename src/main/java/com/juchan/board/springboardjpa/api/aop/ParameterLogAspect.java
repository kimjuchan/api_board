/*
package com.juchan.board.springboardjpa.api.aop;

import com.juchan.board.springboardjpa.common.CreateCommonServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class ParameterLogAspect {

    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void hasController(){
        //Controller 발생 지점을 지정.
    }

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void hasRestController(){
        //RestController 발생 지점을 지정.
    }

    @Around("hasRestController() || hasController()")
    public Object getParamLog(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("===[Param Info]===");
        //TODO : Request 값에 대한 정보를 노출 시킬 예정
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //메서드에 들어가는 매개변수 배열을 읽어옴
        Object[] args = joinPoint.getArgs();
        Method method = methodSignature.getMethod();
        log.info("==[Method Type] : " + method.getName()+"==");
        log.info("===[Param Info]===");
*/
/*

        //매개변수 배열의 종류와 값을 출력
        for(Object obj : args) {
            log.info("type : "+obj.getClass().getSimpleName());
            log.info("value : "+obj);
        }
*//*


        return joinPoint.proceed(args);
    }
}
*/
