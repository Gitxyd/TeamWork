package com.rx.service.impl;

import com.rx.dao.UserloginMapper;
import com.rx.entity.Userlogin;
import com.rx.entity.UserloginExample;
import com.rx.service.UserloginService;
import com.rx.vo.MessageVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserloginServiceImpl implements UserloginService {
    @Autowired
    private UserloginMapper userloginMapper;

    @Override
    public MessageVO login(String username, String password) {
        /*ResultVO vo =  new ResultVO();

        UserloginExample example = new UserloginExample();

        UserloginExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);

        List<Userlogin> users = userloginMapper.selectByExample(example);

        if (users.size() > 0) {
            vo.setSuccess(true);
            vo.setMessage("登录成功！");
            vo.setCode(200);
        } else {
            vo.setCode(-1);
            vo.setMessage("登录失败，当前用户名不存在");
            vo.setSuccess(false);
            vo.setData(null);

        }*/

        MessageVO vo = new MessageVO();

        vo.setCode(-1);
        vo.setMessage("登录失败，当前用户名不存在");
        vo.setSuccess(false);
        vo.setData(null);

        UserloginExample example = new UserloginExample();

        UserloginExample.Criteria criteria = example.createCriteria();

        criteria.andUsernameEqualTo(username);

        List<Userlogin> userlogins = userloginMapper.selectByExample(example);

        // 能查出来说明用户名是存在的
        if (userlogins.size() > 0) {
            Userlogin userlogin = userlogins.get(0);

            // 如果密码相同
            if (userlogin.getPassword().equals(password)) {
                vo.setSuccess(true);
                vo.setMessage("登录成功！");
                vo.setCode(200);
                vo.setData(userlogin);

                // 密码不要传给前端
                userlogin.setPassword(null);
            } else {

                vo.setCode(-200);
                vo.setMessage("登录失败，请输入正确的密码");
                vo.setSuccess(false);
                vo.setData(null);
            }
        }

        return vo;
    }

}



