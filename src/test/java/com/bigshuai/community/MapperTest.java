package com.bigshuai.community;

import com.bigshuai.community.dao.DiscussPostMapper;
import com.bigshuai.community.dao.UserMapper;
import com.bigshuai.community.entity.DiscussPost;
import com.bigshuai.community.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@ContextConfiguration(classes = CommunityApplication.class) // 以它配置类
@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;



//自动补全名称和属性  option command v
    @Test
    public void testSelectUser() {
        User user = userMapper.selectById(101);
        System.out.println(user);

        User user1 = userMapper.selectByName("liubei");
        System.out.println(user1);

        User user2 = userMapper.selectByEmail("nowcoder101@sina.com");
        System.out.println(user2);

    }

    @Test
    public void testInsertUser(){
        User user = new User();
        user.setUsername("bigshuai");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("nowcoder1000@sina.com");
        user.setHeaderUrl("http://wwww.nowcoder.com/101.png");
        user.setCreateTime(new Date());

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }

    @Test
    //mysql 进行操作会返回修改的行数
    public void testUpdateUser(){
        int rows = userMapper.updateStatus(151,1);
        System.out.println(rows);

        rows = userMapper.updateHeader(151,"http://wwww.nowcoder.com/1002.png");
        System.out.println(rows);


    }

    @Test
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        for(DiscussPost post : list) {
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(rows);
    }
}
