package com.rx.service.impl;

import com.rx.dao.CollegeMapper;
import com.rx.entity.College;
import com.rx.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;

    //查询所有课程
    @Override
    public List<College> findAll() {
        return collegeMapper.selectByExample(null);
    }
}
