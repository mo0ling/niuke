package com.example.niuke.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    private int id;//表明这个评论发出的早晚顺序
    private int userId;//表明这个评论发出的用户
    private int entityType;//表示评论的类型 1表示回复帖子的评论 2表示回复评论的评论
    private int entityId;//该评论的帖子id 这个评论的是哪一个
    private int targetId;//表示回复时回复对象的id，如果id=0说明这是一条回复帖子的评论，如果id!=0说明这是回复target_id用户的评论
    private String content;
    private int status;//评论的状态0表示有用的评论 1表示以删除的评论
    private Date createTime;
}
