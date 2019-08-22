package com.example.community.Mapper;

import com.example.community.Model.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Insert(value = "insert into public.user (account_id,name,token,gmt_create,gmt_modified,avatar_url) values (#{account_id},#{name},#{token},#{gmt_create},#{gmt_modified},#{avatar_url})")
    void insert(User us);
    @Select(value = "select * from user where token =#{token}")
    User selectUserByToken(@Param("token") String token);
    @Select(value = "select * from user where id = #{id}")
    User findById(@Param("id") Integer creator);
    @Select(value = "select * from user where account_id = #{account_id}")
    User findByAccountId(@Param("account_id") String account_id);
    @Update("update user set name =#{name},token=#{token},avatar_url=#{avatar_url},gmt_modified=#{gmt_modified} where account_id=#{account_id}")
    void updateByUser(User dbUser);
}
