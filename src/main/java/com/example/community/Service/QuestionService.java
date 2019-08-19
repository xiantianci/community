package com.example.community.Service;

import com.example.community.Dto.QuestionDto;
import com.example.community.Mapper.QuestionMapper;
import com.example.community.Mapper.UserMapper;
import com.example.community.Model.Question;
import com.example.community.Model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public List<QuestionDto> selectAllQuestion(){
        List<Question> questionList = questionMapper.selectAll();
        List<QuestionDto> questionDtoList=new ArrayList<>();
        for (Question question : questionList){
            Integer creator = question.getCreator();
            User user = userMapper.findById(creator);
            QuestionDto questionDto =new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }

        return questionDtoList;
    }
}
