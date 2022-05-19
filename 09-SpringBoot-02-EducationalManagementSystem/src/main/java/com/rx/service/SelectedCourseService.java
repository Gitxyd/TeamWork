
package com.rx.service;


import com.rx.entity.Course;
import com.rx.entity.Selectedcourse;

import java.util.List;

public interface SelectedCourseService {
    // 选课操作
    List<Selectedcourse> finOne(Integer id, Integer studentId);

    // 选课操作
    void add(Integer id, Integer studentId);

    // 退课操作
    void deleteByStudent(Integer id, Integer studentId);

    //已选课程
    List<Course> findCourse(Integer id);
    // 已修课程
    List<Course> findCourseByMark(Integer id);


}

