package com.juchan.board.springboardjpa.api.article.controller;



import com.juchan.board.springboardjpa.api.article.dto.ArticleView;
import com.juchan.board.springboardjpa.api.article.service.ArticleServiceImpl;
import com.juchan.board.springboardjpa.common.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.text.NumberFormat;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleServiceImpl articleService;


    //2024.03.19
    //TODO :  Paging 처리 , 검색 조건 추가(dto,searchForm.html,Querydls)
    @GetMapping("/list")
    public String getArticleList(Model mv, @PageableDefault(page=1) Pageable pageInfo){
        Page<ArticleView> articleViewList = articleService.findAll(pageInfo).map(ArticleView::entityToArticleVeiw);

        log.info("total page : " + articleViewList.getTotalPages());
        log.info("total page : " + articleViewList.getTotalElements());
        log.info("total page : " + articleViewList.getSize());
        log.info("total page : " + articleViewList.getNumber());

        PageUtil page = new PageUtil(pageInfo, (int) articleViewList.getTotalElements());

        mv.addAttribute("list" , articleViewList);
        mv.addAttribute("pageUtil" , page);

        //log.info("totalCnt :: " + articleViewList.getTotalElements())

        return "view/article/list";
    }

    @GetMapping("/detail/{id}")
    public String getArticleById(@PathVariable("id") Long id, ModelAndView mv){
        //id 기반 해당 정보 조회
        ArticleView articleView = ArticleView.entityToArticleVeiw(articleService.findById(id));
        mv.addObject("Article",articleView);
        return "view/article/detail";
    }


}
