package com.rx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.dao.StudentMapper;
import com.rx.entity.Student;
import com.rx.entity.StudentExample;
import com.rx.service.StudentService;
import com.rx.vo.ResultVO;
import com.rx.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    //显示学生信息实现类
    @Override
    public PageInfo<StudentVO> page(Integer page, Integer pageSize) {
        //开启分页
        PageHelper.startPage(page, pageSize);

//        List<Student> list = studentMapper.pageInfo();
        //获取学生集合
        List<StudentVO> list = studentMapper.findAll(null);
        //把集合放入分页里
        return new PageInfo<>(list);
    }

    //搜索学生实现类
    @Override
    public List<Student> findByName(String findStudentByName, Integer page, Integer pageSize) {
        StudentExample studentExample = new StudentExample();

        // studentExample.setUsername("%" + findStudentByName + "%");
        StudentExample.Criteria criteria = studentExample.createCriteria();

        criteria.andUsernameLike("%" + findStudentByName + "%");

        List<Student> list = studentMapper.selectByExample(studentExample);

        return list;
    }

    //学生验证
    @Override
    public ResultVO findById(Integer id) {
        ResultVO vo;

        vo = new ResultVO(true, "");

        Student student = studentMapper.selectByPrimaryKey(id);

        if (student != null) {
            vo = new ResultVO(false, "该学号已经存在，请重新输入！");
        }

        return vo;
    }

}
