package com.rx.service.impl;

import com.rx.dao.CollegeMapper;
import com.rx.entity.College;
import com.rx.entity.CollegeExample;
import com.rx.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class
CollegeServiceImpl implements CollegeService {
    @Autowired
    private CollegeMapper collegeMapper;

    @Override
    public List<College> findAll() {
        CollegeExample example = new CollegeExample();

        CollegeExample.Criteria criteria = example.createCriteria();

        criteria.andCollegeidIsNotNull();

        System.out.println("查询数据库");

        return collegeMapper.selectByExample(example);
    }
}
