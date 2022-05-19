package com.rx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.dao.TeacherMapper;
import com.rx.dao.UserloginMapper;
import com.rx.entity.College;
import com.rx.entity.Teacher;
import com.rx.entity.Userlogin;
import com.rx.service.AdminService;
import com.rx.vo.ResultVO;
import com.rx.vo.TeacherVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private UserloginMapper userloginMapper;


    //查询教师所有信息以及院系名称
    @Override
    public PageInfo<TeacherVO> page(Integer page, Integer pageSize) {
        //开启分页
        PageHelper.startPage(page, pageSize);

        //获取数据
        List<TeacherVO> list = teacherMapper.findAll();

        return new PageInfo<>(list);
    }

    //根据id查询教师是否存在
    @Override
    public ResultVO selectById(Integer id) {
        ResultVO vo;
        vo = new ResultVO(false, "该工号已经存在，请重新输入！");

        Teacher teacher = teacherMapper.selectByPrimaryKey(id);

        if (teacher == null) {
            vo = new ResultVO(true, "");
        }
        return vo;
    }


    //增加教师
    @Override
    public ResultVO add(Teacher teacher) {
        ResultVO vo;
        int affectedRows = teacherMapper.insertSelective(teacher);

        vo = new ResultVO(false, "工号重复！");

        if (affectedRows > 0) {
            vo = new ResultVO(true, "");

            //添加到登录表
            Userlogin userlogin = new Userlogin();
            userlogin.setUsername("" + teacher.getUserid());
            userlogin.setPassword("123");
            userlogin.setRole(1);

            userloginMapper.insertSelective(userlogin);
            System.out.println("userlogin:" + userlogin.toString());
            System.out.println("teacher:" + teacher.toString());
        }
        return vo;
    }

    //根据id查找教师
    @Override
    public ResultVO selectId(Integer id) {
        ResultVO vo;
        vo = new ResultVO(false, "未找到该教师");

        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        if (teacher != null) {
            vo = new ResultVO(true, "");
        }
        return vo;
    }

    //根据指定id查找教师
    @Override
    public Teacher selectTea(Integer id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    //修改教师信息
    @Override
    public void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    //删除教师
    @Override
    public void delete(Integer id) {
        teacherMapper.deleteByPrimaryKey(id);
    }

    //模糊查询教师
    @Override
    public PageInfo<TeacherVO> pageSelect(Integer page, Integer pageSize, String teacherName) {
        if (teacherName != null && !teacherName.equals("")) {
            teacherName = "%" + teacherName + "%";
        }
        //开启分页
        PageHelper.startPage(page, pageSize);

        //获取对象
        List<TeacherVO> list = teacherMapper.selectByName(teacherName);

        return new PageInfo<>(list);
    }

    //查询所有教师
    @Override
    public List<Teacher> findAll() {
        return teacherMapper.selectByExample(null);
    }
}
