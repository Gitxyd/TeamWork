package com.rx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.entity.Student;
import com.rx.service.StudentService;
import com.rx.vo.ResultVO;
import com.rx.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private StudentService studentService;

    //学生信息显示
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "showStudent")
    public ModelAndView showStudent(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                    @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                    ModelAndView mv) {

        PageInfo<StudentVO> pageInfo = studentService.page(page, pageSize);
        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("forward:/pages/admin/showStudent.jsp");

        return mv;
    }

    //学生搜索
    @PostMapping("selectStudent")
    public ModelAndView selectStudent(HttpServletRequest request,
                                      @RequestParam(value = "page", defaultValue = "1") Integer page,
                                      @RequestParam(value = "pageSize", defaultValue = "4") Integer pageSize,
                                      ModelAndView mv) {

        String findStudentByName = request.getParameter("findStudentByName");

        System.out.println(findStudentByName);

        //开启分页
        PageHelper.startPage(page, pageSize);

        List<Student> list = studentService.findByName(findStudentByName, page, pageSize);

        System.out.println(list);

        PageInfo<Student> selectStudentInfo = new PageInfo(list);

        mv.addObject("selectStudentInfo", selectStudentInfo);

        mv.setViewName("forward:/pages/admin/showStudent.jsp");

        return mv;
    }

    //学生验证（添加学生时，看学号是否存在）
    @PostMapping(value = "checkStudentId")
    @ResponseBody
    public ResultVO checkStudentId(Integer id) {
        return studentService.findById(id);

    }
}
