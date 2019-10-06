package com.zhongruan.service.impl;
import com.github.pagehelper.PageHelper;
import com.zhongruan.bean.Role;
import com.zhongruan.bean.UserInfo;
import com.zhongruan.dao.IRoleDao;
import com.zhongruan.dao.IUserInfoDao;
import com.zhongruan.service.IUserInfoService;
import javafx.print.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.xml.registry.JAXRException;
import javax.xml.registry.LifeCycleManager;
import javax.xml.registry.infomodel.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service("userInfoService")
public class UserInfoServiceImpl implements IUserInfoService{
    @Autowired
    IUserInfoDao userInfoDao;
    @Autowired
    //@Autowired 注入依赖!必须要有
    IRoleDao roleDao;
    @Override
    public List<UserInfo>findAll(int page,int size){
        PageHelper.startPage(page,size);
        return userInfoDao.findAll(page,size);
    }
    @Override
    public boolean login(UserInfo userInfo) {
       UserInfo user= userInfoDao.login(userInfo);
       if(user!=null)
           return true;
       else
           return false;
    }
    public void add(UserInfo userInfo) {
        userInfoDao.add(userInfo);
    }
    public void updateUser(UserInfo userInfo){
        userInfoDao.updateUser(userInfo);
    }
    public UserInfo search(int id){
        return userInfoDao.search(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
        UserInfo userInfo = userInfoDao.findByUserName(username);
        List<Role> roles = roleDao.findRoleByUserId(userInfo.getId());
        User user = new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword()
                ,getAuthority(roles));
        return user;
    }

    @Override
    public void delete(int id) {
        userInfoDao.delete(id);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        userInfoDao.deleteAll(ids);
    }

    private Collection<? extends GrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list =new ArrayList<>();
        for(Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
     }
}
