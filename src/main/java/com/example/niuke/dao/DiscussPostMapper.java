package com.example.niuke.dao;

import com.example.niuke.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    // offset 每一页起始行行号; limit 每页最多显示多少条数据
    // userId用于在sql中动态拼接
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit, int orderMode);

    // @Param注解用于给参数起别名
    // 如果只有一个参数 在sql中需要动态拼接参数(使用<if>) 那么参数必须起别名
    int selectDiscussPostsRows(@Param("userId") int userId);

    int insertDiscussPost(DiscussPost discussPost);

    DiscussPost selectDiscussPostById(int id);

    int updateCommentCount(int id, int commentCount);

    int updateType(int id, int type);

    int updateStatus(int id, int status);

    int updateScore(int id, double score);
}
