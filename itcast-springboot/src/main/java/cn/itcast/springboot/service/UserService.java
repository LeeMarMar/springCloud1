package cn.itcast.springboot.service;

import cn.itcast.springboot.mapper.UserMapper;
import cn.itcast.springboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User queryUserById(Long id) {
        return (User) userMapper.selectByPrimaryKey(id);
    }

    public List<User> queryAllUser() {
        return userMapper.selectAll();
    }
}
