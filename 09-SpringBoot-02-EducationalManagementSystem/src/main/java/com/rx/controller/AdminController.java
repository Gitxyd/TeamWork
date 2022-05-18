package com.rx.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.entity.College;
import com.rx.entity.Student;
import com.rx.entity.Teacher;
import com.rx.entity.Userlogin;
import com.rx.service.AdminService;
import com.rx.service.CollegeService;
import com.rx.service.StudentService;
import com.rx.service.UserLoginService;
import com.rx.vo.ResultVO;
import com.rx.vo.StudentVO;
import com.rx.vo.TeacherVO;
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
    @Autowired
    private AdminService adminService;
    @Autowired
    private CollegeService collegeService;
    @Autowired
    private UserLoginService userLoginService;


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

    //添加学生信息界面
    @GetMapping("addStudent")
    public ModelAndView addStudentShow(ModelAndView mv) {

        List<College> list = collegeService.findAll();

        mv.addObject("collegeList", list);

        mv.setViewName("forward:/pages/admin/addStudent.jsp");

        return mv;
    }

    //添加学生信息
    @PostMapping("addStudent")
    public ModelAndView addStudent(Student student, ModelAndView mv) {
        ResultVO vo = null;

        System.out.println("Student:" + student);
        if (student.getUserid() != null) {
            vo = studentService.add(student);
        }

        if (vo.getFlag().equals(true)) {
            Userlogin userlogin = new Userlogin();
            ;

            userlogin.setUsername(student.getUserid().toString());
            userlogin.setPassword("123");
            userlogin.setRole(2);
            userLoginService.save(userlogin);

            //添加成功后重定向
            mv.setViewName("redirect:/admin/showStudent");

            return mv;
        } else {
            mv.addObject("message", "学号重复");

            //添加失败后转发到指定位置
            mv.setViewName("forward:/pages/error.jsp");

            return mv;
        }
    }

    //修改学生信息显示
    @GetMapping("editStudent")
    public ModelAndView editStudentShow(Integer id, ModelAndView mv) throws Exception {
        if (id == null) {

            mv.setViewName("redirect:/admin/showStudent");

            return mv;
        }

        ResultVO vo = studentService.findById(id);

        if (vo.getFlag()) {
            throw new Exception("未找到该名学生");
        } else {
            //查询所有系别
            List<College> list = collegeService.findAll();

            //查找学生
            Student student = studentService.findByStudentId(id);

            mv.addObject("collegeList", list);
            mv.addObject("student", student);

            mv.setViewName("forward:/pages/admin/editStudent.jsp");

            return mv;
        }
    }

    //修改学生信息
    @PostMapping("editStudent")
    public ModelAndView editStudent(Student student, ModelAndView mv) {

        studentService.updateById(student.getUserid(), student);

        mv.setViewName("redirect:/admin/showStudent");

        return mv;
    }

    //删除学生
    @GetMapping("removeStudent")
    public ModelAndView removeStudent(Integer id, ModelAndView mv) {
        if (id == null) {
            mv.setViewName("redirect:/admin/showStudent");

            return mv;
        }

        studentService.removeById(id);
        userLoginService.removeByName(Integer.toString(id));

        mv.setViewName("redirect:/admin/showStudent");

        return mv;
    }

    //学生姓名保存
    @PostMapping("searchStudentName")
    @ResponseBody
    public void searchStudentName(@RequestBody Student student, HttpServletRequest request) {
        String username = student.getUsername();

        request.getSession().setAttribute("findStudentByName", username);
    }


    //查询教师所有信息以及课程
    @RequestMapping(method = {RequestMethod.POST, RequestMethod.GET}, value = "showTeacher")
    public ModelAndView showTeacher(@RequestParam(defaultValue = "1") Integer page,
                                    @RequestParam(defaultValue = "5") Integer pageSize,
                                    ModelAndView mv) {
        PageInfo<TeacherVO> teacherPageInfo = adminService.page(page, pageSize);

        mv.addObject("teacherPageInfo", teacherPageInfo);
        mv.setViewName("forward:/pages/admin/showTeacher.jsp");

        return mv;
    }

    //教师工号验证
    @PostMapping("checkTeacherId")
    @ResponseBody
    public ResultVO checkTeacherId(@RequestParam Integer id) {
        return adminService.selectById(id);
    }

    //添加教师页面显示
    @GetMapping("addTeacher")
    public ModelAndView addTeacher(ModelAndView mv) {

        List<College> list = collegeService.findAll();

        mv.addObject("collegeList", list);
        mv.setViewName("forward:/pages/admin/addTeacher.jsp");

        return mv;
    }

    //添加教师信息
    @PostMapping("addTeacher")
    public ModelAndView addTeaches(Teacher teacher, ModelAndView mv) {

        ResultVO vo = adminService.add(teacher);

        if (vo.getFlag()) {
            mv.setViewName("redirect:/admin/showTeacher");
        } else {
            mv.addObject("message", vo.getErrorMsg());
            mv.setViewName("forward:/pages/error.jsp");
        }
        return mv;
    }

    //修改教师页面显示
    @GetMapping("editTeacher")
    public ModelAndView editTeacher(Integer id, ModelAndView mv) {

        if (id == null) {
            mv.setViewName("redirect:/admin/showTeacher");
        }

        //查询id
        ResultVO vo = adminService.selectId(id);

        //存在
        if (vo.getFlag()) {
            Teacher teacher = adminService.selectTea(id);
            mv.addObject("teacher", teacher);

            List<College> list = collegeService.findAll();
            mv.addObject("collegeList", list);

            mv.setViewName("forward:/pages/admin/editTeacher.jsp");
        } else {
            //不存在
            try {
                throw new Exception(vo.getErrorMsg());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mv;
    }

    //修改教师信息
    @PostMapping("editTeacher")
    public ModelAndView editTeachers(Teacher teacher, ModelAndView mv) {

        //添加教师
        adminService.update(teacher);

        mv.setViewName("redirect:/admin/showTeacher");
        return mv;
    }

    //删除教师
    @GetMapping("removeTeacher")

    public ModelAndView removeTeacher(Integer id, ModelAndView mv) {

        //id为空
        if (id != null) {
            //删除教师
            adminService.delete(id);

            //删除登录表
            userLoginService.deleteByName(Integer.toString(id));
        }
        mv.setViewName("redirect:/admin/showTeacher");
        return mv;
    }


    //保存教师姓名
    @PostMapping("searchTeacherName")
    public void searchTeacherName(Teacher teacher,
                                  HttpServletRequest request) {
        request.getSession().setAttribute("findTeacherByName", teacher.getUsername());
    }
}
