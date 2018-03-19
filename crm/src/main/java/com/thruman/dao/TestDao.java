package com.thruman.dao;

import com.thruman.pojo.Test;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author niexiang
 * @Description
 * @create 2018-03-19 17:57
 **/
@Repository
public interface TestDao {

    @Select("select * from test where id = #{id}")
    public Test getId(int id);

}