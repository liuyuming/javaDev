package com.thruman.service;

import com.thruman.pojo.Test;
import com.thruman.pojo.User;

import java.util.List;

public interface TestService {
    public Test getTest(int id);

    List<User> getUserAll();

}
