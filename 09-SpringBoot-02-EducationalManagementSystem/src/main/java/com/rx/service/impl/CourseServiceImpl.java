package com.rx.service.impl;

import com.rx.dao.CourseMapper;
import com.rx.dao.SelectedcourseMapper;
import com.rx.entity.Course;
import com.rx.service.CourseService;
import com.rx.vo.SelectedCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private SelectedcourseMapper selectedcourseMapper;

    //查询学生课程关联表加学生姓名
    @Override
    public List<SelectedCourseVO> selectInfo(Integer id) {
        return courseMapper.findAll(id);
    }

    @Override
    public List<Course> selectByUserId(int parseInt) {
        return null;
    }

    //根据课程id查询学生课程关联表加学生姓名
    @Override
    public SelectedCourseVO updateByStudent(Integer studentid, Integer courseid, String name, Integer mark) {

        SelectedCourseVO vo = selectedcourseMapper.updateByKey(studentid, courseid, mark);
        if (vo != null){
            vo.setOver(true);
        }
        return vo;
    }

    //根据课程id与学生id查找学生
    @Override
    public SelectedCourseVO selectByIdName(Integer studentid, Integer courseid) {
        SelectedCourseVO vo;
        vo = courseMapper.selectByIdName(studentid, courseid);
        return vo;
    }
}
