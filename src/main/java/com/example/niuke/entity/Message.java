package com.example.niuke.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    private int id;
    private int fromId;//发消息的人的id 1系统发的
    private int toId;//发送目标的人的id
    private String conversationId;//冗余的字段目的是便于查询，规则以from_id和to_id用_拼接且小的在前大的在后
    private String content;
    private int status;//0表示未读 1表示已读 2表示删除
    private Date createTime;
}
