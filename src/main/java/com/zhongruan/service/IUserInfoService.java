package com.zhongruan.service;

import com.zhongruan.bean.UserInfo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserInfoService  extends UserDetailsService {
    List<UserInfo> findAll(int page,int size);
    boolean login(UserInfo userInfo);
    void add(UserInfo userInfo);
    void updateUser(UserInfo userInfo);
    UserInfo search(int id);
  UserDetails loadUserByUsername(String username);
   void delete (int id);
    void deleteAll(List<Integer> ids);
}
