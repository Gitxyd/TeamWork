package com.rx.service;

import com.github.pagehelper.PageInfo;
import com.rx.entity.Student;
import com.rx.vo.ResultVO;
import com.rx.vo.StudentVO;

import java.util.List;

public interface StudentService  {
    PageInfo<StudentVO> page(Integer page, Integer pageSize);

    List<Student> findByName(String findStudentByName, Integer page, Integer pageSize);

    ResultVO findById(Integer id);
}
