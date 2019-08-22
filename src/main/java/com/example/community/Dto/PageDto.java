package com.example.community.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PageDto {
    private List<QuestionDto> questionDtoList;
    private boolean showPrevious;
    private boolean showFirst;
    private boolean showLast;
    private boolean showPost;
    private Integer page;
    private Integer totalPage;
    private List<Integer> pages=new ArrayList<>();

    public void setPagination(Integer totalCount, Integer totalPage, Integer page, Integer size) {
        this.page=page;
        this.totalPage=totalPage;
        pages.add(page);
        for (int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
            }
            if(page+i<=totalPage){
                pages.add(page+i);
            }
        }
        //判断是否有上一页
        if(page!=1){
            showPrevious=true;
        }
        else {
            showPrevious=false;
        }
        //判断下一页
        if(page!=totalPage){
            showPost=true;
        }
        else {
            showPost=false;
        }
        //判断首页
        if(pages.contains(1)){
            showFirst=false;
        }
        else {
            showFirst=true;
        }
        //判断尾页
        if(pages.contains(totalPage)){
            showLast=false;
        }
        else {
            showLast=true;
        }

    }
}
