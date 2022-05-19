package com.rx.service;

import com.rx.entity.Course;
import com.rx.vo.SelectedCourseVO;

import com.github.pagehelper.PageInfo;
import com.rx.entity.Course;
import com.github.pagehelper.PageInfo;
import com.rx.entity.Course;
import com.rx.vo.ResultVO;

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

    PageInfo<Course> findByPage(Integer page, Integer pageSize);

    ResultVO findById(Integer id);

    List<Course> findAll();

    ResultVO add(Course course);

    Course findByCourseId(Integer id);

    int updateById(Integer courseid, Course course);

    int removeByName(Integer id);

    List<Course> findCourseByName(String findCourseByName, Integer page, Integer pageSize);

}
