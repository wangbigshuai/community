package com.bigshuai.community.dao;

import com.bigshuai.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
//
import java.util.List;
//之后建立个配置文件
@Mapper//有了 才能够被装配
public interface DiscussPostMapper {
    //userId 动态sql 为0 和不为0    //分页 起始行行号 每页最多数据
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //@param 注解用于给参数取别名，如果只有一个参数 并且在<if>里使用 则必须加别名
    int selectDiscussPostRows(@Param("userId")int userId);



}
