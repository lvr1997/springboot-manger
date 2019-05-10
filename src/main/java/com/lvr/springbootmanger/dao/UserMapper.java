package com.lvr.springbootmanger.dao;

import com.lvr.springbootmanger.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectUserList(User user);

    long countUsers(User user);

    int deleteUser (@Param("userId") Integer userId);

    User queryUserByUserAccountandPassword(@Param("userAccount") String userAccount , @Param("userPassword") String userPassword);


}