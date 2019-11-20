package com.yc.music.controller;

import com.yc.music.mq.ActiveMQProducer;
import com.yc.music.service.UserService;
import com.yc.music.vo.MQEventVo;
import com.yc.music.vo.UserVo;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/*
 * @ClassName：UserController
 * @author:  cun.yan@cloud-linking.net
 * @CreateDate: 2019/10/9 17:13
 * @UpdateUser: cun.yan@cloud-linking.net
 * @UpdateDate: 2019/10/9 17:13
 * @UpdateRemark:第一版
 * @Description: 用户Api请求类
 * @Version: [V1.0]
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActiveMQProducer activeMqProducer;

    @PostMapping(value = "/insert", produces = "application/json")
    public ResponseEntity<String> insert(@RequestBody String params) throws Exception {

        JSONObject jsonObject = JSONObject.parseObject(params);
        UserVo userVo = JSON.toJavaObject(jsonObject, UserVo.class);
        //userService.insertUser(userVo);
        MQEventVo mqEventVo = new MQEventVo("userService", "insertUser", userVo,UserVo.class);
        String userVoString = JSON.toJSONString(mqEventVo);
        activeMqProducer.sendQueueMessage(userVoString);
        activeMqProducer.sendTopicMessage(userVoString);
        return new ResponseEntity<>(HttpStatus.OK);


    }


    @GetMapping(value = "/get", produces = "application/json")
    public UserVo get() throws Exception {

        UserVo userVo = new UserVo();
        userVo.setAddress("1234566");
        userVo.setGender(1);
        userVo.setPassword("123456");
        userVo.setUserId(123456789L);
        userVo.setUserName("yancun");
        return userVo;


    }

}
