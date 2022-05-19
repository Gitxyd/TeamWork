package com.rx.controller;

import com.rx.entity.Course;
import com.rx.service.CourseService;
import com.rx.vo.SelectedCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("teacher")
public class TeacherController {
    @Autowired
    private CourseService courseService;

    //显示课程
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "showCourse")
    public ModelAndView showCourse(HttpSession session, ModelAndView mv){

        String userName = (String) session.getAttribute("userlogin");
        System.out.println(userName);

        //查询全部
        List<Course> list = courseService.selectByUserId(Integer.parseInt(userName));

        mv.addObject("courseList",list);
        mv.setViewName("forward:/pages/teacher/showCourse.jsp");

        return mv;
    }

    //显示成绩
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "gradeCourse")
    public ModelAndView gradeCourse(Integer id, ModelAndView mv){

        if (id != null){

            List<SelectedCourseVO> list = courseService.selectInfo(id);

            mv.addObject("selectedCourseList", list);
            mv.setViewName("forward:/pages/teacher/showGrade.jsp");
        }
        return mv;
    }

    //打分页面
    @GetMapping("mark")
    public ModelAndView mark(@RequestParam Integer studentid,
                             @RequestParam Integer courseid,
                             ModelAndView mv){
        SelectedCourseVO vo = courseService.selectByIdName(studentid,courseid);

        mv.addObject("selectedCourse",vo);
        mv.setViewName("forward:/pages/teacher/mark.jsp");
        return mv;
    }

    //打分操作
    @PostMapping("mark")
    public ModelAndView markT(@RequestParam Integer studentid,
                              @RequestParam Integer courseid,
                              @RequestParam String name,
                              @RequestParam Integer mark,
                              ModelAndView mv){
        //打分操作，对学生成绩进行修改
        courseService.updateByStudent(studentid, courseid, name, mark);

        mv.addObject("id", courseid);
        mv.setViewName("redirect:/teacher/gradeCourse");

        return mv;
    }

    //修改密码
    @GetMapping("passwordRest")
    public ModelAndView passwordRest(ModelAndView mv){

        mv.setViewName("forward:/pages/teacher/passwordRest.jsp");

        return mv;
    }
}
