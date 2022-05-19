package com.rx.controller;

import com.github.pagehelper.PageInfo;
import com.rx.entity.Course;
import com.rx.entity.Selectedcourse;
import com.rx.entity.Student;
import com.rx.entity.Userlogin;
import com.rx.service.CourseService;
import com.rx.service.SelectedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("student")
public class StudentController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SelectedCourseService selectedCourseService;

    // 展示课程
    @GetMapping("showCourse")
    public ModelAndView showCourse(@RequestParam(value = "page", defaultValue = "1") Integer pageNum,
                                   @RequestParam(defaultValue = "4") Integer pageSize,
                                   ModelAndView mv){
        // 分页查询学生课程
        PageInfo<Course> CoursePageInfo = courseService.showCourse(pageNum, pageSize);

        mv.addObject("CoursePageInfo", CoursePageInfo);

        mv.setViewName("forward:/pages/student/showCourse.jsp");

        return mv;
        }


    // 已选课程
    @GetMapping("selectedCourse")
    public ModelAndView selectedCourse(ModelAndView mv,
                                       HttpSession session) {

        // 获取登录的学生用户名
        Userlogin userlogin = (Userlogin) session.getAttribute("userlogin");

        // 获取登录的用户名
        String username = userlogin.getUsername();

        //获取登录学生的(entity用的就是)studentId（登录账户就是学生userId）
        Integer id = Integer.parseInt(username);

        List<Course> selectedcourseVos = selectedCourseService.findCourse(id);

        mv.addObject("selectedCourse",selectedcourseVos);

        mv.setViewName("forward:/pages/student/selectCourse.jsp");
        return mv;
    }


       // 已修课程
    @GetMapping("overCourse")
    public ModelAndView overCourse(ModelAndView mv, HttpSession session){

        // 获取登录的学生用户名
         Userlogin userlogin = (Userlogin) session.getAttribute("userlogin");

        // 获取登录的用户名
        String username = userlogin.getUsername();

        //获取登录学生的(entity用的就是)studentId（登录账户就是学生userId）
        Integer id = Integer.parseInt(username);

        List<Course> list = selectedCourseService.findCourseByMark(id);

        System.out.println(list);

        mv.addObject("selectedCourseList", list);

        mv.setViewName("forward:/pages/student/overCourse.jsp");

        return mv;
    }

    // 选课操作
    @GetMapping("stuSelectedCourse")
    public ModelAndView stuSelectedCourse(Integer id,
                                          HttpSession session,
                                         ModelAndView mv) {
        // 获取登录对象
        Userlogin userlogin = (Userlogin) session.getAttribute("userlogin");

        // 获取登录的用户名
        String username = userlogin.getUsername();

        //获取登录学生的(entity用的就是)studentId（登录账户就是学生userId）
        Integer studentId = Integer.parseInt(username);

        // 查询指定学生课程（已选课程）
        List<Selectedcourse> selectedcourse =selectedCourseService.finOne(id, studentId);

        if (selectedcourse != null) {
            mv.addObject("message","该门课程你已经选了，不能再选");
            mv.setViewName("error");
        } else {
            //没有插入到数据库
            selectedCourseService.add(id,studentId);
            mv.setViewName("redirect:/pages/student/selectedCourse.jsp");
        }

        return mv;
    }

    // 退课操作
    @GetMapping("outCourse")
    public ModelAndView outCourse(Integer id, HttpSession session, ModelAndView mv){
        // 获取登录的学生用户名(我登录用对象来接收了 封装了一层)
        Userlogin userlogin = (Userlogin) session.getAttribute("userlogin");

        //获取登录学生的userId
        String username = userlogin.getUsername();

        //获取登录学生的(entity用的就是studentId 前后端要一一对应 报错了看控制台的参数）（登录账户就是学生userId）
        Integer studentId = Integer.parseInt(username);

        // 通过学生的课程id 和学生id删除学生已修课程
        selectedCourseService.deleteByStudent(id,studentId);

        mv.setViewName("redirect:/pages/student/selectedCourse.jsp");

        return mv;
    }


    //将查询的 课程名称存入session中
    @PostMapping("searchCourseName")
    @ResponseBody
    public void searchCourseName(Student student,
                              HttpSession session){
        String courseName = student.getUsername();
        //将查询的 课程名称存入session中
       session.setAttribute("findCourseByName",courseName);
    }


    //搜索课程
    @PostMapping("searchCourse")
    private ModelAndView searchCourse(String findByName,
                                      @RequestParam(value = "page",required = true,defaultValue = "1")Integer page,
                                      @RequestParam(value = "pageSize",required = true,defaultValue = "4")Integer pageSize,
                                      ModelAndView mv){

        List<Course> courseByKeyword = courseService.findCourseByKeyword(findByName,page,pageSize);

        PageInfo<Course> selectCourseInfo=new PageInfo<>(courseByKeyword);

        mv.addObject("searchCourseInfo",selectCourseInfo);

        mv.setViewName("forward:/pages/student/searchCourse.jsp");

        return mv;

    }

    //修改密码
    @GetMapping("passwordRest")
    public String passwordRest(){

        return "forward:/pages/student/passwordRest.jsp";
    }
    /*@GetMapping("passwordRest")
    public ModelAndView passwordRest(ModelAndView mv) {
        mv.setViewName("forward:/pages/student/passwordRest.jsp");
        return mv;
    }*/
}
