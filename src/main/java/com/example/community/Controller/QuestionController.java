package com.example.community.Controller;

import com.example.community.Dto.QuestionDto;
import com.example.community.Mapper.QuestionMapper;
import com.example.community.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Integer id,
                           Model model){
        QuestionDto questionDto=questionService.getById(id);
        model.addAttribute("questionDto",questionDto);
        return "question";
    }
}
