package com.xdclass.mobile.xdclassmobileredis.controller;

import com.alibaba.fastjson.JSON;
import com.xdclass.mobile.xdclassmobileredis.RedisService;
import com.xdclass.mobile.xdclassmobileredis.domain.User;
import com.xdclass.mobile.xdclassmobileredis.mapper.UserMapper;
import com.xdclass.mobile.xdclassmobileredis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {

    private static final String key = "userCache_";

    @Resource
    UserMapper userMapper;

    @Resource
    private UserService userService;

    @Resource
    RedisService redisService;

    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(String id){
        System.out.println("直接查数据库"+id);
        return userMapper.find(id);
    }

    @RequestMapping("/getUserCache")
    @ResponseBody
    public User getUserCache(String id){
        User user = (User)redisService.get(key + id);
        if (user == null){
            User userDB = userMapper.find(id);
            redisService.set(key+id , userDB);
            return userDB;
        }
        return user;
    }

    @RequestMapping("/getByCache")
    @ResponseBody
    public User getByCache(String id) {
        User user = userService.findById(id);
        System.out.println("查缓存"+id);
        return user;
    }

    @ResponseBody
    @RequestMapping(value = "/getexpire", method = RequestMethod.GET)
    public User findByIdTtl(String id) {
        User u = new User();
        try{
            u = userService.findByIdTtl(id);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
        return u;
    }

    @RequestMapping("/updateByCache")
    @ResponseBody
    public User updateByCache(User user) {
        user = userService.updateUser(user);
        return user;
    }
}
