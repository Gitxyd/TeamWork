package com.rx.dao;

import com.rx.entity.Teacher;
import com.rx.entity.TeacherExample;
import java.util.List;

import com.rx.vo.TeacherVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {
    long countByExample(TeacherExample example);

    int deleteByExample(TeacherExample example);

    int deleteByPrimaryKey(Integer userid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    List<Teacher> selectByExample(TeacherExample example);

    Teacher selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByExample(@Param("record") Teacher record, @Param("example") TeacherExample example);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

    //查询教师所有信息以及院系名称
    List<TeacherVO> findAll();

    //根据名字获取所有教师
    List<TeacherVO> selectByName(@Param("teacherName") String teacherName);
}