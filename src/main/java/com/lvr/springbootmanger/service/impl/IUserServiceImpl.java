package com.lvr.springbootmanger.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lvr.springbootmanger.bean.User;
import com.lvr.springbootmanger.dao.UserMapper;
import com.lvr.springbootmanger.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class IUserServiceImpl implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public PageInfo<User> selectUserList(User user ,Integer pageNum) {

        PageHelper.startPage(pageNum,5);//根据当前页和每页的记录数，调用pagehelp进行分页处理

        List<User> userList = userMapper.selectUserList(user);//从数据库中查询出list集合
        PageInfo<User> userPageInfo = new PageInfo<>(userList);//把list集合放到pageInfo
        userPageInfo.setTotal(userMapper.countUsers(user));//把总条数放到pageInfo里面

        return userPageInfo;
    }

    @Override
    public int insertSelective(User record) {
        int  flag = userMapper.insertSelective(record);
        if(flag !=0){

            return  flag;
        }
        return 0;
    }

    @Override
    public User selectByPrimaryKey(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        return user;
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        int  flag= userMapper.updateByPrimaryKeySelective(record);
        return flag;
    }

    @Override
    public int deleteUser(Integer userId) {

        return userMapper.deleteUser(userId);
    }

    @Override
    public User queryUserByUserAccountandPassword(String userAccount, String userPassword) {


        return userMapper.queryUserByUserAccountandPassword(userAccount,userPassword);
    }
}
