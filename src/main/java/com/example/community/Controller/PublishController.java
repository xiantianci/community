package com.example.community.Controller;

import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.Question;
import com.example.community.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/publish")
    public String publish(){
        return "publish";
    }
    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title")String title,
            @RequestParam("tag")String tag,
            @RequestParam("description")String description,
            HttpServletRequest request,
            Model model
    ){
        model.addAttribute("title",title);
        model.addAttribute("tag",tag);
        model.addAttribute("description",description);
        if(title==null||title==""){
            model.addAttribute("error","标题不能为空");
            return "publish";
        }
        if(tag==null||tag==""){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        if(description==null||description==""){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }
        User user = new User();
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")){
                String token = cookie.getValue();
                user=userMapper.selectUserByToken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
            }
        }
        if(user==null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }
        Question question = new Question();
        question.setTitle(title);
        question.setTag(tag);
        question.setDescription(description);
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modified(question.getGmt_create());
        question.setCreator(user.getId());
        questionMapper.create(question);
        return "redirect:/";
    }
}
