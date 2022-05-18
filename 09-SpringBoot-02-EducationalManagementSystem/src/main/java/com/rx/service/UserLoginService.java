package com.rx.service;

import com.rx.entity.Userlogin;
import com.rx.vo.MessageVO;

public interface UserLoginService {
    MessageVO login(String username, String password);

    void deleteByName(String toString);


    void removeByName(String toString);

    void save(Userlogin userlogin);
}
