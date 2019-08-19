package com.example.community.Dto;

import com.example.community.Model.User;
import lombok.Data;

@Data
public class QuestionDto {
    private Integer id;
    private String title;
    private String description;
    private String tag;
    private long gmt_create;
    private long gmt_modified;
    private Integer creator;
    private Integer comment_count;
    private Integer view_count;
    private Integer like_count;
    private User user;
}
