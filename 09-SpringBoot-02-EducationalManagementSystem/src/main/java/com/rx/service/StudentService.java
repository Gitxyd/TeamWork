package com.rx.service;

import com.github.pagehelper.PageInfo;
import com.rx.entity.Student;

import com.rx.entity.StudentExample;
import com.rx.vo.ResultVO;
import com.rx.vo.StudentVO;

import java.util.List;

public interface StudentService {
    PageInfo<Student> showCourse(Integer pageNum, Integer pageSize, Integer userId);

    PageInfo<StudentVO> page(Integer page, Integer pageSize);

    List<Student> findByName(String findStudentByName, Integer page, Integer pageSize);

    ResultVO add(Student student);


    int updateById(Object userid, Student student);

    int removeById(Integer id);

    ResultVO findById(Integer id);

    Student findByStudentId(Integer id);
}
