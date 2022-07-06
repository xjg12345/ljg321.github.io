package com.example.weather6.controller;

import com.example.weather6.entity.Db01;
import com.example.weather6.mapper.Db01Mapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/Db01")
public class Db01Controller {

    @Resource
    Db01Mapper db01Mapper;

    @GetMapping
    public List<Db01> getDb01(){
        return db01Mapper.findAll();
    }
}
