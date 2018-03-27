package com.thruman.service.impl;

import com.thruman.dao.cluster.OrderMapper;
import com.thruman.dao.master.AccountMapper;
import com.thruman.pojo.Account;
import com.thruman.pojo.Order;
import com.thruman.service.MutilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class MutilServiceImpl implements MutilService{
    @Resource
    AccountMapper accountMapper;
    @Resource
    OrderMapper orderMapper;


    @Transactional
    public void test() {
        Account account = new Account();
        account.setBalance(12312l);
        account.setUserId("niexiang001");
        account.setCreateTime(new Date());
        accountMapper.insert(account);
        Order order = new Order();
        order.setCount(99999);
        order.setUserId("niexiang002");
        order.setNumber("1231");
        order.setCreateTime(new Date());
        orderMapper.insert(order);
    }
}
