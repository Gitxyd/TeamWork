package com.rx.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.dao.CollegeMapper;
import com.rx.dao.CourseMapper;
import com.rx.dao.SelectedcourseMapper;
import com.rx.entity.Course;
import com.rx.entity.CourseExample;
import com.rx.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CollegeMapper collegeMapper;

    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

    //展示课程
    @Override
    public PageInfo<Course> showCourse(Integer pageNum, Integer pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        List<Course> courses = courseMapper.selectByExample(null);

        return new PageInfo<>(courses);
    }

    // 搜索课程
    @Override
    public List<Course> findCourseByKeyword(String findCourseByName, Integer page, Integer pageSize) {
        CourseExample courseExample = new CourseExample();

        CourseExample.Criteria criteria = courseExample.createCriteria();

        criteria.andCoursenameLike("%" + findCourseByName + "%");

        // 开启分页
        PageHelper.startPage(page, pageSize);

         return courseMapper.selectByExample(courseExample);

    }

}
