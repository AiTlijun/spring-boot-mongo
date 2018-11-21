package com.example.springbootmongo.controller;


import com.alibaba.fastjson.JSON;
import com.example.springbootmongo.entity.User;
import com.example.springbootmongo.entity.UserLog;
import com.example.springbootmongo.response.UploadPicRequest;
import com.example.springbootmongo.service.UserLogService;
import com.example.springbootmongo.service.jpa.UserService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    private MockMvc mockMvc; // 模拟MVC对象，通过MockMvcBuilders.webAppContextSetup(this.wac).build()初始化。

    @Autowired
    private WebApplicationContext wac; // 注入WebApplicationContext

//    @Autowired
//    private MockHttpSession session;// 注入模拟的http session
//
//    @Autowired
//    private MockHttpServletRequest request;// 注入模拟的http request\

    @Before // 在测试开始前初始化工作
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    /*@Test
    public void userControllerTest() throws Exception {
        User user = new User();
        user.setId(1);
        user.setCity("深圳");
        user.setUserName("李军");
        MvcResult result = mockMvc.perform(post("/file/uploadPic").contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(user)))
                .andExpect(status().isOk())// 模拟向testRest发送get请求
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))// 预期返回值的媒体类型text/plain;charset=UTF-8
                .andReturn();// 返回执行请求的结果

        System.out.println(result.getResponse().getContentAsString());

    }*/
    @Test
    public void test1() throws Exception {
        List<User> userList = new ArrayList<User>();
        for(int i =5;i< 9;i++){
            User user = new User();
            user.setExperience(i);
            user.setScore(i);
            user.setSex((byte) i);
            user.setSign("Sign" + i);
            user.setUserName("阿凡达" + i);
            user.setWealth((long) (i * 10));
            user.setClassify("Classify" + i);
            user.setCity("city" + i);
            userList.add(user);
        }

        /*String userStr = new Gson().toJson(userList);
        System.out.println("userStr:"+userStr);*/
        UploadPicRequest uploadPicRequest = new UploadPicRequest();
        File file1 = new File("D:\\test1.jpg");
        File file2 = new File("D:\\test2.jpg");
        File file3 = new File("D:\\test3.jpg");
        List<File> files = new ArrayList<File>();
        files.add(file1);
        files.add(file2);
        files.add(file3);
        uploadPicRequest.setPics(files);
        uploadPicRequest.setUsers(userList);
        String uploadPicRequestStr = new Gson().toJson(uploadPicRequest);
        System.out.println("uploadPicRequestStr:"+uploadPicRequestStr);
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/file/uploadPic")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(uploadPicRequestStr)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("SUCCESS")));

        MvcResult mvcResult = resultActions.andReturn();
        System.out.println("mvcResult:"+mvcResult.getResponse().getContentAsString());

    }



}

