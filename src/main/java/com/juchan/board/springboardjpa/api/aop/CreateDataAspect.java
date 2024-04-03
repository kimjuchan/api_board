package com.juchan.board.springboardjpa.api.aop;

import com.juchan.board.springboardjpa.api.article.service.ArticleServiceImpl;
import com.juchan.board.springboardjpa.api.articlecomment.service.ArticleCommentServiceImpl;
import com.juchan.board.springboardjpa.common.CreateCommonServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Slf4j
@Aspect
@Component
public class CreateDataAspect {

    @Autowired
    private CreateCommonServiceImp createCommonServiceImp;


    @Pointcut("execution(* com.juchan.board.springboardjpa.api.main.controller.MainController.*(..))")
    public void isPointCut(){
        //발생 지점을 지정.
        //TODO : 추후에 로그인 기능 구현 완료되면 로그인 페이지에서 정상적으로 로그인 Success 시점에 Data 추가 작업 진행.

    }

    @Around("isPointCut()")
    public Object isAround(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("this is around annotation start");
        Object result = null;

        result = joinPoint.proceed(joinPoint.getArgs());

        //메인 url 진입 감지 후 random data insert 진행. (
        this.createdDataStart();
        return result;
    }

    //service
    @Before("@annotation(com.juchan.board.springboardjpa.api.aop.annotation.CreateDataList)")
    public void createdDataStart() throws IOException {
        log.info("----------[[Init Create Data start ]]----------");
        createCommonServiceImp.creatArticle("Article");
        createCommonServiceImp.creatArticleComment("Article_comment");
    }
    @AfterReturning("@annotation(com.juchan.board.springboardjpa.api.aop.annotation.CreateDataList)")
    public void createdDataAfter(){
        log.info("----------[[Init Create Data end ]]----------");
    }
}
