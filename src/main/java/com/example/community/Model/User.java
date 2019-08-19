package com.example.community.Model;

import lombok.Data;

@Data
public class User {
    private int id;
    private String name;
    private String account_id;
    private String token;
    private long gmt_create;
    private long gmt_modified;
    private String avatar_url;
}
