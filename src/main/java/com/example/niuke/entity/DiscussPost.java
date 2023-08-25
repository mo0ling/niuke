package com.example.niuke.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "discusspost")//indexName:索引名，type：固定_doc,shards：分片，replicas:备份
public class DiscussPost {

    @Id
    private int id;

    @Field(type = FieldType.Integer)
    private int userId;

    // ik_smart 为最少切分，ik_max_word为最细粒度划分
    //analyzer:存储时的解析器  互联网校招--->建立最大的索引（就是各种拆分）
    //searchAnalyzer：搜索时的解析器 拆分尽可能少的满足意图的分词器
    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_max_word", searchAnalyzer = "ik_smart")
    private String content;

    @Field(type = FieldType.Integer)
    private int type;//0普通 1置顶

    @Field(type = FieldType.Integer)
    private int status;//0正常 1精华 2拉黑

    @Field(type = FieldType.Date)
    private Date createTime;

    @Field(type = FieldType.Integer)
    private int commentCount;//帖子的评论数，是一个冗余参数，目的是为了提供查询效率

    @Field(type = FieldType.Double)
    private double score;
}
