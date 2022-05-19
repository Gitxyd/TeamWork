package com.rx.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rx.dao.CollegeMapper;
import com.rx.dao.CourseMapper;
import com.rx.dao.SelectedcourseMapper;
import com.rx.entity.Course;
import com.rx.entity.CourseExample;
import com.rx.service.CourseService;
import com.rx.vo.ResultVO;
import com.rx.vo.SelectedCourseVO;
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
        if (vo != null) {
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

    //课程信息显示
    @Override
    public PageInfo<Course> findByPage(Integer page, Integer pageSize) {
        //开启分页
        PageHelper.startPage(page, pageSize);

        List<Course> list = courseMapper.selectByExample(null);

        return new PageInfo<>(list);
    }

    //课号验证
    @Override
    public ResultVO findById(Integer id) {
        ResultVO vo;

        vo = new ResultVO(true, "");

        Course course = courseMapper.selectByPrimaryKey(id);

        if (course != null) {
            vo = new ResultVO(false, "该学号已经存在，请重新输入！");
        }

        return vo;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    //添加课程
    @Override
    public ResultVO add(Course course) {
        ResultVO resultVO = new ResultVO();

        int affectedRows = courseMapper.insertSelective(course);

        if (affectedRows > 0) {
            resultVO.setErrorMsg("添加成功");
            resultVO.setFlag(true);
        } else {
            resultVO.setErrorMsg("添加失败");
            resultVO.setFlag(false);
        }
        return resultVO;
    }

    //修改课程页面显示
    @Override
    public Course findByCourseId(Integer id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    //修改课程
    @Override
    public int updateById(Integer courseid, Course course) {
        return courseMapper.updateByPrimaryKeySelective(course);
    }

    @Override
    public int removeByName(Integer id) {
        return courseMapper.deleteByPrimaryKey(id);
    }

    //搜索课程
    @Override
    public List<Course> findCourseByName(String findCourseByName, Integer page, Integer pageSize) {
        CourseExample courseExample = new CourseExample();

        CourseExample.Criteria criteria = courseExample.createCriteria();

        criteria.andCoursenameLike("%" + findCourseByName + "%");

        List<Course> list = courseMapper.selectByExample(courseExample);

        return list;
    }
}
