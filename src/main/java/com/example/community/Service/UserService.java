package com.example.community.Service;

import com.example.community.Mapper.UserMapper;
import com.example.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    public void creatOrUpdateUser(User  user, HttpServletRequest request) {
        User dbUser =userMapper.findByAccountId(user.getAccount_id());
        if(dbUser==null){
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
            request.getSession().setAttribute("user",user);
            //插入操作
        }else {
            dbUser.setGmt_modified(System.currentTimeMillis());
            dbUser.setAvatar_url(user.getAvatar_url());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());
            userMapper.updateByUser(dbUser);
            request.getSession().setAttribute("user",dbUser);
        }
    }
}
