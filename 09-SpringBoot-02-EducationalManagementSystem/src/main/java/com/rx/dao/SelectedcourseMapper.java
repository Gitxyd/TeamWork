package com.rx.dao;

import com.rx.entity.Course;
import com.rx.entity.Selectedcourse;
import com.rx.entity.SelectedcourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedcourseMapper {
    long countByExample(SelectedcourseExample example);

    int deleteByExample(SelectedcourseExample example);

    int insert(Selectedcourse record);

    int insertSelective(Selectedcourse record);

    List<Selectedcourse> selectByExample(SelectedcourseExample example);

    int updateByExampleSelective(@Param("record") Selectedcourse record, @Param("example") SelectedcourseExample example);

    int updateByExample(@Param("record") Selectedcourse record, @Param("example") SelectedcourseExample example);

    //通过标记打分查询是默认已选课程(有mark分数是已修)
    List<Course> findCourseByMark(Integer id);
}