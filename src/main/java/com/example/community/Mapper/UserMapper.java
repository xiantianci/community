package com.example.community.Mapper;

import com.example.community.Model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Insert(value = "insert into public.user (account_id,name,token,gmt_create,gmt_modified) values (#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    void insert(User us);
    @Select(value = "select * from user where token =#{token}")
    User selectUserByToken(@Param("token") String token);
}
