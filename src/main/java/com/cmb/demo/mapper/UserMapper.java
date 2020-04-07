package com.cmb.demo.mapper;

import com.cmb.demo.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    List<User> getUserInfoByTime(@Param(value = "time") String time);
}
