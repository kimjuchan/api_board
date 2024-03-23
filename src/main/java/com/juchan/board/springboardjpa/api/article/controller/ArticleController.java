package com.juchan.board.springboardjpa.api.article.controller;



import com.juchan.board.springboardjpa.api.article.dto.ArticleView;
import com.juchan.board.springboardjpa.api.article.service.ArticleServiceImpl;
import com.juchan.board.springboardjpa.common.PageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    //TODO :  Paging 처리 , 검색 조건 추가(dto,searchForm.html,Querydls)
    @GetMapping("/list")
    public String getArticleList(Model mv, @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageInfo){

        Page<ArticleView> articleViewList = articleService.findAll(pageInfo).map(ArticleView::entityToArticleVeiw);
        PageUtil page = new PageUtil(pageInfo, (int) articleViewList.getTotalElements(),articleViewList.getTotalPages());
        //page index 값은 0부터 시작이고   해당 현재 요청 page 값은 1부터 시작임.
        mv.addAttribute("list" , articleViewList);
        mv.addAttribute("pageUtil" , page);

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
