package com.example.community.Controller;

import com.example.community.Dto.PageDto;
import com.example.community.Mapper.UserMapper;
import com.example.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String index(HttpServletRequest request, Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size" ,defaultValue = "5")Integer size){
        PageDto pageDto =questionService.selectAllQuestion(page,size);
        model.addAttribute("pageDto",pageDto);
        //User user = (User) request.getSession().getAttribute("user");
        return "index";
    }

}
