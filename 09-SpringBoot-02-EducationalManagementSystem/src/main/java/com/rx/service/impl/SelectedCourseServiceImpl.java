package com.rx.service.impl;


import com.rx.dao.SelectedcourseMapper;
import com.rx.entity.Course;
import com.rx.entity.Selectedcourse;
import com.rx.entity.SelectedcourseExample;
import com.rx.service.SelectedCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SelectedCourseServiceImpl implements SelectedCourseService {
    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

    @Override
    public List<Selectedcourse> finOne(Integer id, Integer studentId) {
        SelectedcourseExample selectedcourseExample = new SelectedcourseExample();

        SelectedcourseExample.Criteria criteria = selectedcourseExample.createCriteria();

        criteria.andStudentidEqualTo(studentId);
        criteria.andCourseidEqualTo(id);

        return selectedcourseMapper.selectByExample(selectedcourseExample);
    }

    @Override
    public void add(Integer id, Integer studentId) {
        Selectedcourse selectedcourse = new Selectedcourse();

        selectedcourse.setStudentid(studentId);
        selectedcourse.setCourseid(id);

        selectedcourseMapper.insertSelective(selectedcourse);
    }

    @Override
    public void deleteByStudent(Integer id, Integer studentId) {
        SelectedcourseExample selectedcourseExample = new SelectedcourseExample();

        SelectedcourseExample.Criteria criteria = selectedcourseExample.createCriteria();

        criteria.andStudentidEqualTo(studentId);
        criteria.andCourseidEqualTo(id);

        selectedcourseMapper.deleteByExample(selectedcourseExample);
    }

    // 已选课程
    @Override
    public List<Course> findCourse(Integer id) {
        List<Course> courses = selectedcourseMapper.findCourseByMark(id);
        return courses;
    }

    // 已修课程
    @Override
    public List<Course> findCourseByMark(Integer id) {

        List<Course> courses = findCourse(id);

        for (Course course : courses) {
            if (course.getMark() != null) {
                course.setOver(true);
            } else {
                course.setOver(false);
            }
        }

        return courses;
    }
}