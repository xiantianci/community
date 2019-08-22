package com.example.community.Service;

import com.example.community.Dto.PageDto;
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

    public PageDto selectAllQuestion(Integer page, Integer size){
        Integer totalCount = questionMapper.count();
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }
        else {
            totalPage=totalCount/size+1;
        }
        if (page<1){
            page=1;
        }
        if(page>totalPage&&totalPage>1){
            page=totalPage;
        }
        Integer offset = (page-1)*size;
        List<Question> questionList = questionMapper.selectAll(offset,size);
        List<QuestionDto> questionDtoList=new ArrayList<>();
        PageDto pageDto = new PageDto();
        for (Question question : questionList){
            Integer creator = question.getCreator();
            User user = userMapper.findById(creator);
            QuestionDto questionDto =new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestionDtoList(questionDtoList);

        pageDto.setPagination(totalCount,totalPage,page,size);
        return pageDto;
    }

    public PageDto selectQuestionsByUser(Integer id, Integer page, Integer size) {

        Integer totalCount = questionMapper.countByUserId(id);
        Integer totalPage;
        if(totalCount%size==0){
            totalPage=totalCount/size;
        }
        else {
            totalPage=totalCount/size+1;
        }
        if (page<1){
            page=1;
        }
        if(page>totalPage&&totalPage>1){
            page=totalPage;
        }
        Integer offset = (page-1)*size;
        List<Question> questionList = questionMapper.selectQuestionsById(id,offset,size);
        List<QuestionDto> questionDtoList=new ArrayList<>();
        PageDto pageDto = new PageDto();
        for (Question question : questionList){
            Integer creator = question.getCreator();
            User user = userMapper.findById(creator);
            QuestionDto questionDto =new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        pageDto.setQuestionDtoList(questionDtoList);

        pageDto.setPagination(totalCount,totalPage,page,size);
        return pageDto;
    }

    public QuestionDto getById(Integer id) {
        Question question = questionMapper.selectById(id);
        QuestionDto questionDto = new QuestionDto();
        User user = userMapper.findById(question.getCreator());
        BeanUtils.copyProperties(question,questionDto);
        questionDto.setUser(user);
        return  questionDto;
    }

    public void createOrUpdate(Question question) {
        if(question.getId()==null){
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modified(question.getGmt_create());
            questionMapper.create(question);
        }else {
            question.setGmt_modified(System.currentTimeMillis());
            questionMapper.updateById(question);
        }
    }
}
