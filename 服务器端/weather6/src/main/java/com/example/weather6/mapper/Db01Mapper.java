package com.example.weather6.mapper;

import com.example.weather6.entity.Db01;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Db01Mapper {
    @Select("select * from db01")
    List<Db01> findAll();
}
