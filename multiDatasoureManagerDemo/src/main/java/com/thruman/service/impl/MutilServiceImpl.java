package com.thruman.service.impl;

import com.thruman.dao.cluster.OrderMapper;
import com.thruman.dao.master.AccountMapper;
import com.thruman.service.MutilService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class MutilServiceImpl implements MutilService{
    @Resource
    AccountMapper AccountMapper;
    @Resource
    OrderMapper orderMapper;


    public void test() {
        System.out.println(AccountMapper.selectAll());
        System.out.println(orderMapper.selectAll());
    }
}
