package com.rx.service.impl;

import com.rx.dao.UserloginMapper;
import com.rx.entity.UserloginExample;
import com.rx.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserloginMapper userloginMapper;

    //根据教师名字删除对应教师登录用户
    @Override
    public void deleteByName(String toString) {

        //声明标准模板
        UserloginExample example = new UserloginExample();

        UserloginExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(toString);

        userloginMapper.deleteByExample(example);
    }
}
