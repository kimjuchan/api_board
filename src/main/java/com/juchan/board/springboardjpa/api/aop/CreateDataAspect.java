package com.juchan.board.springboardjpa.api.aop;

import com.juchan.board.springboardjpa.api.article.service.ArticleServiceImpl;
import com.juchan.board.springboardjpa.api.articlecomment.service.ArticleCommentServiceImpl;
import com.juchan.board.springboardjpa.common.CreateCommonServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Aspect
@Component
public class CreateDataAspect {

    @Autowired
    private CreateCommonServiceImp createCommonServiceImp;

    //service
    @Before("@annotation(com.juchan.board.springboardjpa.api.aop.CreateDataList)")
    public void createdDataStart() throws IOException {
        log.info("----------[[Init Create Data start ]]----------");
        createCommonServiceImp.creatArticle("Article");
        createCommonServiceImp.creatArticleComment("Article_comment");
    }
    @AfterReturning("@annotation(com.juchan.board.springboardjpa.api.aop.CreateDataList)")
    public void createdDataAfter(){
        log.info("----------[[Init Create Data end ]]----------");
    }
}
