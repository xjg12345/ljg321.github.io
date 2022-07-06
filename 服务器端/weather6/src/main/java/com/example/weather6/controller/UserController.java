package com.example.weather6.controller;

import com.example.weather6.entity.User;
import com.example.weather6.mapper.UserMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {
    @Resource
    UserMapper userMapper;

    @GetMapping("/getMethod")
    public List<User> getUser(){
        return userMapper.findAll();
    }

    @PostMapping("/postMethod")
    public String addUser(@RequestBody User user){
        userMapper.save(user);
        return "success";
    }

    @PutMapping("/putMethod")
    public String updateUser(@RequestBody User user){
        userMapper.updateById(user);
        return "success";
    }

    @DeleteMapping("/deleteMethod/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userMapper.deleteById(id);
        return "success";
    }

    @GetMapping("/getMethod/{id}")
    public User findById(@PathVariable("id") Long id){
        return userMapper.findById(id);
    }

}
