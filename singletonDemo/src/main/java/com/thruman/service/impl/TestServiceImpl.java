package com.thruman.service.impl;

import com.thruman.dao.TestDao;
import com.thruman.dao.UserMapper;
import com.thruman.pojo.Test;
import com.thruman.pojo.User;
import com.thruman.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author niexiang
 * @Description
 * @create 2018-03-19 17:56
 **/
@Service
public class TestServiceImpl implements TestService {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

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

    public void sleep() {
        try {
            logger.info("睡眠开始------------------------" + new Date());
            Thread.sleep(5000);
            logger.info("睡眠结束------------------------" + new Date());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}