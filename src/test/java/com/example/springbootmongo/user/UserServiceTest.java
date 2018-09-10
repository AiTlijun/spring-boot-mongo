package com.example.springbootmongo.user;

import com.example.springbootmongo.entity.User;
import com.example.springbootmongo.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void test() throws Exception {
        Date d = new Date();
        System.out.println("begin:"+d.getTime());
        User u = userService.getUserById(13);
        Date d2 = new Date();
        System.out.println("end:"+(d2.getTime() - d.getTime())+"    name:"+u.getUserName());

       // Assert.assertEquals("11111", u.getId());
    }

    @Test
    public void test1() throws Exception {
        Set<byte[]> keySet = redisTemplate.keys("*".getBytes());
        byte[][] keys = keySet.toArray(new byte[keySet.size()][]);
        // 获取所有value

        // Assert.assertEquals("11111", u.getId());
        System.out.println("begin:"+keySet.size());

    }
}
//1536567079853  1536567081078  1890  1055
//1536567152330 1536567153372