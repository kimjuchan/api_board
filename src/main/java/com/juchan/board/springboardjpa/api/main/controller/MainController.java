package com.juchan.board.springboardjpa.api.main.controller;

import com.juchan.board.springboardjpa.api.aop.CreateDataList;
import com.juchan.board.springboardjpa.api.article.service.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/main")
public class MainController {

    private final ArticleServiceImpl articleService;

    @CreateDataList
    @GetMapping("/index")
    public void indexPage(){
        //index page 호출하면서 필요한 데이터 insert 진행.
    }

}
