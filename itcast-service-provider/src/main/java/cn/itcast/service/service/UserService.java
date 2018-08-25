package cn.itcast.service.service;

import cn.itcast.service.dao.UserMapper;
import cn.itcast.service.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUserById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
}
