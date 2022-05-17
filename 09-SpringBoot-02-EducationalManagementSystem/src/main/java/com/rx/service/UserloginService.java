package com.rx.service;

import com.rx.vo.MessageVO;

public interface UserloginService {
    // 登录
    MessageVO login(String username, String password);

    void deleteByName(String toString);
}
