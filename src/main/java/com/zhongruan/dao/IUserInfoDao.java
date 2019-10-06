package com.zhongruan.dao;

import com.zhongruan.bean.UserInfo;

import javax.jws.soap.SOAPBinding;
import java.util.List;
//dao层与MyBatis中的xml对应，SQL
public interface IUserInfoDao {
    List<UserInfo> findAll(int page,int size);
    UserInfo login(UserInfo userInfo);
    void add(UserInfo userInfo);
    void updateUser(UserInfo userInfo);
    UserInfo search(int id);
    UserInfo findByUserName(String username);
    void delete (int id);
    void deleteAll(List<Integer> ids);
}
