package com.rx.controller;

import com.rx.entity.Userlogin;
import com.rx.service.UserloginService;
import com.rx.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserloginController {
     @Autowired
     private UserloginService userloginService;

    @PostMapping("login")
    public ModelAndView login(Userlogin userlogin,ModelAndView mv, HttpSession session) {
        // 通过vo封装获取账户密码
        MessageVO vo = userloginService.login(userlogin.getUsername(), userlogin.getPassword());


        // 权限管理
        if (vo.getCode() == 200 && ((Userlogin)vo.getData()).getRole().equals(0)) {
            //admin login
            //账户密码存到session中
            session.setAttribute("userlogin", vo.getData());

            mv.addObject("resultInfo", vo);

            mv.setViewName("redirect:/admin/showStudent");
        } else if (vo.getCode() == 200 && ((Userlogin)vo.getData()).getRole().equals(1)){
            //teacher login
            session.setAttribute("userlogin", vo.getData());

            mv.addObject("resultInfo", vo);


            mv.setViewName("redirect:/admin/showCourse");

        } else if (vo.getCode() == 200 && ((Userlogin)vo.getData()).getRole().equals(2)){
           ///student login
            session.setAttribute("userlogin", vo.getData());

            mv.addObject("resultInfo", vo);

            mv.setViewName("redirect:/admin/showCourse");
        } else {
            mv.setViewName("forward:/login.jsp");
        }

        return mv;
    }
    // 退出登录
    @GetMapping("logout")
    public ModelAndView logout(HttpSession session, HttpServletResponse response, ModelAndView mv) {

        Cookie cookie = new Cookie("JSESSIONID", null);

        // 立即销毁cookie
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        session.removeAttribute("userlogin");

        // 销毁session
        session.invalidate();

        // 重定向到登录页面
        mv.setViewName("redirect:/login.jsp");
        return mv;
    }




}
