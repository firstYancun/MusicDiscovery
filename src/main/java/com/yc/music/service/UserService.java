package com.yc.music.service;


import com.yc.music.mapper.UserMapper;
import com.yc.music.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @ClassName：UserService
 * @author:  cun.yan@cloud-linking.net
 * @CreateDate: 2019/10/9 17:13
 * @UpdateUser: cun.yan@cloud-linking.net
 * @UpdateDate: 2019/10/9 17:13
 * @UpdateRemark:第一版
 * @Description: 用户服务类
 * @Version: [V1.0]
 */
@Slf4j
@Service("userService")
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 新增用户信息
     *
     * @param userVo 插入user的参数
     * @throws Exception
     */
    public void insertUser(UserVo userVo) throws Exception {
        try {
            log.info("Start to insert user info.");
            userMapper.insertUser(userVo);
            log.info("end to insert user info.");
        } catch (Exception ex) {
            throw ex;
        }
    }


}
