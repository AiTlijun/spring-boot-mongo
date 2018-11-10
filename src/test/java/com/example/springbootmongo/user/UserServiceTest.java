package com.example.springbootmongo.user;

import com.example.springbootmongo.entity.User;
import com.example.springbootmongo.entity.UserLog;
import com.example.springbootmongo.service.UserLogService;
import com.example.springbootmongo.service.jpa.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private UserLogService userLogService;

    private int ThreadNum = 2000;


    @Test
    public void test() throws Exception {
        Date d = new Date();
        System.out.println("begin:" + d.getTime());
        User u = userService.getUserById(13);
        Date d2 = new Date();
        System.out.println("end:" + (d2.getTime() - d.getTime()) + "    name:" + u.getUserName());

        // Assert.assertEquals("11111", u.getId());
    }

    @Test
    public void addUserCountDownLatch() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(ThreadNum);
        long start = System.currentTimeMillis();
        for (int i = 0; i < ThreadNum; i++) {
            LatchDemo ld = new LatchDemo(countDownLatch);
            new Thread(ld).start();
        }
        // 等待所有线程操作完成
        System.out.println("当等待所有线程操作完成");

        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("耗费时间为:" + (end - start));
    }

    class LatchDemo implements Runnable {
        private final CountDownLatch countDownLatch;

        public LatchDemo(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }
        public void run() {

            System.out.println("当前线程:" + Thread.currentThread().getName() + "准备就绪, 等待countdown为0后开始运行....");

            UserLog userLog = new UserLog();
            userLog.setUsername( Thread.currentThread().getId()+"");
            userLog.setOperation("admin");
            userLog.setOperationdate(new Date());
            userLogService.insert(userLog);
            try {
                // 线程结束操作
                countDownLatch.countDown();
            }catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("当前线程:" + Thread.currentThread().getName() + "准备就绪, 添加数据成功....");
        }
    }

    @Test
    public void addUser() throws Exception {
        for (int i = 1; i < 3000; i++) {
            /*User user = new User();
            user.setExperience(i);
            user.setScore(i);
            user.setSex((byte) 1);
            user.setSign("Sign" + i);
            user.setUserName("阿凡达" + i);
            user.setWealth((long) (i * 10));
            user.setClassify("Classify" + i);
            user.setCity("city" + i);
            userService.addUser(user);*/
            UserLog userLog = new UserLog();
            userLog.setUsername("阿凡达-" + Thread.currentThread().getName());
            userLog.setOperation("admin");
            userLog.setOperationdate(new Date());
            userLogService.insert(userLog);
        }
        /*Date d = new Date();
        System.out.println("begin:" + d.getTime());
        User u = userService.getUserById(13);
        Date d2 = new Date();
        System.out.println("end:" + (d2.getTime() - d.getTime()) + "    name:" + u.getUserName());*/

        // Assert.assertEquals("11111", u.getId());
    }

    @Test
    public void test1() throws Exception {
        Set<byte[]> keySet = redisTemplate.keys("*".getBytes());
        byte[][] keys = keySet.toArray(new byte[keySet.size()][]);
        // 获取所有value

        // Assert.assertEquals("11111", u.getId());
        System.out.println("begin:" + keySet.size());

    }

}
//1536567079853  1536567081078  1890  1055
//1536567152330 1536567153372