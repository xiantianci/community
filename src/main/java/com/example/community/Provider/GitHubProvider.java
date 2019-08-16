package com.example.community.Provider;

import com.alibaba.fastjson.JSON;
import com.example.community.Dto.AccessTokenDto;
import com.example.community.Dto.GitHubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GitHubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] strs = string.split("&");
            String[] tokens = strs[0].split("=");
            String token = tokens[1];
            System.out.println("string:"+string);
            System.out.println("token"+token);
            return token;

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public GitHubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token="+accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GitHubUser gitHubUser = JSON.parseObject(string, GitHubUser.class);
            return gitHubUser;
        }
       catch (IOException e){
            return null;
       }

    }
}
