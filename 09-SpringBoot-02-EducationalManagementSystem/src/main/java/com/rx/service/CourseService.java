package com.rx.service;

import com.rx.entity.Course;
import com.rx.vo.SelectedCourseVO;

import com.github.pagehelper.PageInfo;
import com.rx.entity.Course;

import java.util.List;

public interface CourseService {

    List<SelectedCourseVO> selectInfo(Integer id);


    List<Course> selectByUserId(int parseInt);

    SelectedCourseVO updateByStudent(Integer studentid, Integer courseid, String name, Integer mark);

    SelectedCourseVO selectByIdName(Integer studentid, Integer courseid);
    // 展示课程
    PageInfo<Course> showCourse(Integer pageNum, Integer pageSize);

    // 通过课程名搜索课程（模糊查询）
    List<Course> findCourseByKeyword(String findCourseByName, Integer page, Integer pageSize);
}
