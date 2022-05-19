package com.rx.service;

import com.github.pagehelper.PageInfo;
import com.rx.entity.Teacher;
import com.rx.vo.ResultVO;
import com.rx.vo.TeacherVO;

import java.util.List;

public interface AdminService {
    PageInfo<TeacherVO> page(Integer page, Integer pageSize);

    ResultVO selectById(Integer id);

    ResultVO add(Teacher teacher);

    ResultVO selectId(Integer id);

    Teacher selectTea(Integer id);

    void update(Teacher teacher);

    void delete(Integer id);

    PageInfo<TeacherVO> pageSelect(Integer page, Integer pageSize, String teacherName);

    List<Teacher> findAll();
}
