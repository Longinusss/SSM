package com.zhongruan.controller;
import com.github.pagehelper.PageInfo;
import com.zhongruan.bean.UserInfo;
import com.zhongruan.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("user")
public class UserInfoController {
    @Autowired
    IUserInfoService userInfoService;
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue="1") int page,@RequestParam
            (defaultValue="5") int size) {
        //默认值
        List<UserInfo> userinfos =userInfoService.findAll(page,size);
        PageInfo pageInfo=new PageInfo(userinfos);
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("user-list");//jsp路径
        return mv;
    }
//@RequestMapping("login.do")
//    public ModelAndView login(UserInfo userInfo, HttpServletRequest request){
//        boolean flag= userInfoService.login(userInfo);
//        ModelAndView mv=new ModelAndView();
//        if(null==userInfo.getUsername()){
//           mv.setViewName("../index");
//        }else {
//            if (flag) {
//                mv.setViewName("main");
//                request.getSession().setAttribute("userinfo", userInfo);
//            }else{
//                mv.setViewName("../failer");
//            }
//        }
//        return mv;
//    }
//    //跳转页面
//    @RequestMapping("toAddUser.do")
//    public ModelAndView toAddUser(){
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("addUser");//jsp路径跳转至addUser.jsp页面
//        return mv;
//    }
//
    @RequestMapping("userAdd.do")
    public String userAdd(@RequestParam("username") String username, @RequestParam("password")String password) {
        UserInfo userInfo=new UserInfo(0, username, password);
        userInfoService.add(userInfo);
        return "redirect:/user/findAll.do";//函数返回类型：String,重新执行findAll.do
    }

    @RequestMapping("toUpdate.do")
    public  ModelAndView toUpdate(@RequestParam("id") int id){
        UserInfo userInfo=userInfoService.search(id);
        ModelAndView mv =new ModelAndView();
        mv.addObject("userInfo",userInfo); //把此页面的数据送给页面容器
        mv.setViewName("user-update");//跳转页面
        return mv;
    }

    @RequestMapping("updateUser.do")
    public String updateUser(@RequestParam("id")int id,
                         @RequestParam("username")String username,@RequestParam("password")String password) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setUsername(username);
        userInfo.setPassword(password);
     //System.out.println(userInfo);//测试
        userInfoService.updateUser(userInfo);
        return "redirect:/user/findAll.do";
    }
    @RequestMapping("delete.do")
    public String delete(@RequestParam("id")int id){
        userInfoService.delete(id);//找到user
        return "redirect:/user/findAll.do";
    }

@RequestMapping("deleteAll.do")
@ResponseBody
    public String deleteAll(String userList){
        String[] strs=userList.split(",");
        List<Integer> ids =new ArrayList<>();
        for(int i =0;i<strs.length;i++){
            ids.add(Integer.parseInt(strs[i]));
        }
        userInfoService.deleteAll(ids);
        return "";
    }
}
