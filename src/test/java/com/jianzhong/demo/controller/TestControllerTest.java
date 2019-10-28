//package com.jianzhong.demo.controller;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.web.WebAppConfiguration;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import javax.ws.rs.core.MediaType;
//
//
//@WebAppConfiguration
//public class TestControllerTest {
//
//    @Autowired
//    protected MockMvc mockMvc;
//    @Autowired
//    protected WebApplicationContext wac;
//
//    @Before
//    public void setUp() throws Exception {
//        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();  //初始化MockMvc对象
//        System.out.println("========= set Up =============");
//    }
//
//    @After
////    public void tearDown() throws Exception {
////        System.out.println("========= tear Down =============");
////    }
//
//    @Test
//    public void index() throws Exception {
//        System.out.println("========= index =============");
//        String responseString = mockMvc.perform(
//            get("/categories/getAllCategory")
//                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
//                .param("pcode","root")         //添加参数
//        ).andExpect(status().isOk())    //返回的状态是200
//            .andDo(print())         //打印出请求和相应的内容
//            .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
//        System.out.println("--------返回的json = " + responseString);
//    }
//
//    @Test
//    public void asyncTask() {
//        System.out.println("========= async Task =============");
//    }
//
//}