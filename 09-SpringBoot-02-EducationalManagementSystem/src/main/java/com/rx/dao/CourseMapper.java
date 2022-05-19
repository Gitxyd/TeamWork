package com.rx.dao;

import com.rx.entity.Course;
import com.rx.entity.CourseExample;
import java.util.List;

import com.rx.vo.SelectedCourseVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseMapper {
    long countByExample(CourseExample example);

    int deleteByExample(CourseExample example);

    int deleteByPrimaryKey(Integer courseid);

    int insert(Course record);

    int insertSelective(Course record);

    List<Course> selectByExample(CourseExample example);

    Course selectByPrimaryKey(Integer courseid);

    int updateByExampleSelective(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByExample(@Param("record") Course record, @Param("example") CourseExample example);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKey(Course record);

    //根据课程id查询学生课程关联表加学生姓名
    List<SelectedCourseVO> findAll(@Param("id") Integer id);

    //根据课程id与学生id查找学生
    SelectedCourseVO selectByIdName(@Param("studentId") Integer studentid, @Param("courseId") Integer courseid);
}