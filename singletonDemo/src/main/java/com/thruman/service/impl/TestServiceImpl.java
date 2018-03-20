package com.thruman.service.impl;

import com.thruman.dao.TestDao;
import com.thruman.dao.UserMapper;
import com.thruman.pojo.Test;
import com.thruman.pojo.User;
import com.thruman.service.TestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author niexiang
 * @Description
 * @create 2018-03-19 17:56
 **/
@Service
public class TestServiceImpl implements TestService {
    @Resource
    private TestDao testDao;

    @Resource
    private UserMapper userMapper;

    public Test getTest(int id) {
        return testDao.getId(id);
    }

    public List<User> getUserAll() {
        return userMapper.select(null);
    }
}