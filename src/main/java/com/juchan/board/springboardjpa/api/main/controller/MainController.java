package com.juchan.board.springboardjpa.api.main.controller;

import com.juchan.board.springboardjpa.api.article.service.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/main")
public class MainController {

    private final ArticleServiceImpl articleService;

    @GetMapping("/index")
    public String indexPage(){
        //index page 호출하면서 필요한 데이터 insert 진행.
        return "view/main/index";
    }

}
