package com.rx.service;

import com.rx.vo.MessageVO;

public interface UserLoginService {
    MessageVO login(String username, String password);

    void deleteByName(String toString);
}
