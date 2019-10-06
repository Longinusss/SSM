package com.zhongruan.dao;

import com.zhongruan.bean.Role;

import java.util.List;

public interface IRoleDao {
    List<Role> findRoleByUserId(int id);
}
