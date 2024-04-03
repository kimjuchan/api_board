package com.juchan.board.springboardjpa.api.article.controller;



import com.juchan.board.springboardjpa.api.article.domain.Article;
import com.juchan.board.springboardjpa.api.article.dto.ArticleDetailView;
import com.juchan.board.springboardjpa.api.article.dto.ArticleRequest;
import com.juchan.board.springboardjpa.api.article.dto.ArticleUpdateRequest;
import com.juchan.board.springboardjpa.api.article.dto.ArticleView;
import com.juchan.board.springboardjpa.api.article.service.ArticleServiceImpl;
import com.juchan.board.springboardjpa.api.member.domain.Member;
import com.juchan.board.springboardjpa.api.member.domain.MemberDetail;

import com.juchan.board.springboardjpa.common.page.PageUtil;
import com.juchan.board.springboardjpa.common.search.SearchDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleServiceImpl articleService;

    @GetMapping("/create")
    public String goCreateView(){
        return "view/article/create";
    }

    @PostMapping("/create")
    public String insertByArticle(@AuthenticationPrincipal MemberDetail memberDetail,
                                  @Valid ArticleRequest articleRequest){
        //Set 등록 Member정보
        articleRequest.setMember(memberDetail.member);
        Long createdId = articleService.create(articleRequest);
        //최초 목록 1페이지 랜딩 (임시)
        return "redirect:/article/list";
    }

    @PostMapping("/update")
    public String updateByArticle(@ModelAttribute @Valid ArticleUpdateRequest articleUpdateRequest){
        Long updateId = articleService.updateByArticle(articleUpdateRequest);
        //최초 목록 1페이지 랜딩 (임시)
        return "redirect:/article/detail/" + updateId;
    }

    @GetMapping("/delete/{id}")
    public String deleteByArticle(@PathVariable Long id){
        articleService.deleteByArticle(id);
        return "redirect:/article/list";
    }

    @GetMapping("/list")
    public String getArticleList(
            @AuthenticationPrincipal MemberDetail memberDetail,
            Model mv, SearchDto searchDto,
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageInfo){

        Page<ArticleView> articleViewList = null;
        if(searchDto.getType().equals("ALL")){
            articleViewList = articleService.findAll(pageInfo).map(ArticleView::entityToArticleVeiw);
        }else{
            //검색 조건 있을경우 목록 조회
            articleViewList = articleService.findAllBySearch(searchDto,pageInfo).map(ArticleView::entityToArticleVeiw);
        }

        PageUtil page = new PageUtil(pageInfo, (int) articleViewList.getTotalElements(),articleViewList.getTotalPages());
        //page index 값은 0부터 시작이고   해당 현재 요청 page 값은 1부터 시작임.
        mv.addAttribute("list" , articleViewList);
        mv.addAttribute("pageUtil" , page);
        mv.addAttribute("searchDto", searchDto);

        return "view/article/list";
    }

    @GetMapping("/detail/{id}")
    public String getArticleById(
            @AuthenticationPrincipal MemberDetail memberdetail,
            @PathVariable("id") Long id, Model mv){
        //id 기반 해당 정보 조회
        ArticleDetailView articleView = ArticleDetailView.entityToArticleVeiw(articleService.findById(id));
        mv.addAttribute("article",articleView);
        mv.addAttribute("user",memberdetail);


        return "view/article/detail";
    }


}
