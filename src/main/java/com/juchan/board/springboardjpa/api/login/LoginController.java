package com.juchan.board.springboardjpa.api.login;

import com.juchan.board.springboardjpa.api.member.dto.MemberRequest;
import com.juchan.board.springboardjpa.api.member.service.MemberDetailService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class LoginController {

    private final MemberDetailService memberDetailService;

    //회원가입 등록 폼
    @GetMapping("/sign-up")
    public String signup(){
        return "/view/login/sign-up";
    }

    //회원가입 등록
    @PostMapping("/sign-in")
    public String signin(MemberRequest memberRequest){
        memberDetailService.createUser(memberRequest);
        return "redirect:/view/login/loginPage";
    }

    //로그인 페이지
    @GetMapping("/login")
    public String loginPage(Model model,
                            @RequestParam(value = "error", required = false)String error,
                            @RequestParam(value = "exception", required = false)String exception){

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);
        return "/view/login/loginPage";
    }


    //[Action] 로그아웃 프로세스를 동작시킨다.
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //TODO : 추후에  fiter 쪽으로 로직 옮겨서 처리하는게 더 맞는 것 같음.
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    cookie.setMaxAge(0); // 쿠키 삭제
                    response.addCookie(cookie);
                    break;
                }
            }
        }
        return "redirect:/login";
    }


}
