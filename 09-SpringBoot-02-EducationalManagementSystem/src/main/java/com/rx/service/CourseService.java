package com.rx.service;

import com.rx.entity.Course;
import com.rx.vo.SelectedCourseVO;

import java.util.List;

public interface CourseService {

    List<SelectedCourseVO> selectInfo(Integer id);


    List<Course> selectByUserId(int parseInt);

    SelectedCourseVO updateByStudent(Integer studentid, Integer courseid, String name, Integer mark);

    SelectedCourseVO selectByIdName(Integer studentid, Integer courseid);
}
