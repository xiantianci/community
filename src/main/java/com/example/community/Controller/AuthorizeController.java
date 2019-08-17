package com.example.community.Controller;

import com.example.community.Dto.AccessTokenDto;
import com.example.community.Dto.GitHubUser;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.User;
import com.example.community.Provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String client_id;

    @Value("${github.client.secret}")
    private String client_secret;

    @Value("${github.redirect.uri}")
    private String redirect_uri;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("callback")
    public String callback(@RequestParam(name = "code")String code,
                           @RequestParam(name = "state")String state,
                           HttpServletRequest request){
        AccessTokenDto accessTokenDtoto = new AccessTokenDto();
       // gitHubProvider.getAccessToken(accessTokenDtoto);
        accessTokenDtoto.setCode(code);
        accessTokenDtoto.setRedirect_uri(redirect_uri);
        accessTokenDtoto.setState(state);
        accessTokenDtoto.setClient_id(client_id);
        accessTokenDtoto.setClient_secret(client_secret);
        String token = gitHubProvider.getAccessToken(accessTokenDtoto);
        GitHubUser gitHubUser = gitHubProvider.getUser(token);
        System.out.println("username"+gitHubUser.getId());
        if(gitHubUser!=null){
            request.getSession().setAttribute("user",gitHubUser);
            User user =new User();
            user.setToken(UUID.randomUUID().toString());
            user.setName(gitHubUser.getName());
            user.setAccount_id(String.valueOf(gitHubUser.getId()));
            user.setGmt_create(System.currentTimeMillis());
            user.setGmt_modified(user.getGmt_create());
            userMapper.insert(user);
            return "redirect:/";
        }
        else{
            return "redirect:/";
        }

    }
}
