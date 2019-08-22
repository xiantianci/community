package com.example.community.Mapper;

import com.example.community.Model.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmt_create},#{gmt_modified},#{creator},#{tag})")
    void create(Question question);
    @Select("select * from question limit #{offset},#{size}")
    List<Question> selectAll(@Param("offset")Integer offset,@Param("size")Integer size);
    @Select("select count(1) from question")
    int count();
    @Select("select * from question where creator=#{id} limit #{offset},#{size}")
    List<Question> selectQuestionsById(@Param("id")Integer id,@Param("offset")Integer offset,@Param("size")Integer size);
    @Select("select count(1) from question where creator=#{id}")
    Integer countByUserId(@Param("id")Integer id);
    @Select("select * from question where id = #{id}")
    Question selectById(@Param("id") Integer id);
    @Update("update question set title=#{title},description=#{description},tag=#{tag},gmt_modified=#{gmt_modified} where id=#{id}")
    void updateById(Question question);
}
