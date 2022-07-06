package com.example.weather6.mapper;

import com.example.weather6.entity.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserMapper {
    @Select("select * from user;")
    List<User> findAll();

    @Update("INSERT INTO USER (id, username, password) VALUES (#{id}, #{username}, #{password});")
    @Transactional
    void save(User user);

    @Update("update user set username=#{username}, password=#{password} where id = #{id};")
    @Transactional
    void updateById(User user);

    @Delete("delete from user where id = #{id};")
    void deleteById(Long id);

    @Select("select * from user where id = #{id};")
    User findById(Long id);
}
