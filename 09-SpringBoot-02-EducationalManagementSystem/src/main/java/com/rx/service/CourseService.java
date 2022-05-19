package com.rx.service;

import com.github.pagehelper.PageInfo;
import com.rx.entity.Course;
import com.rx.vo.ResultVO;

import java.util.List;

public interface CourseService {

    PageInfo<Course> findByPage(Integer page, Integer pageSize);

    ResultVO findById(Integer id);

    List<Course> findAll();

    ResultVO add(Course course);

    Course findByCourseId(Integer id);

    int updateById(Integer courseid, Course course);

    int removeByName(Integer id);

    List<Course> findCourseByName(String findCourseByName, Integer page, Integer pageSize);
}
