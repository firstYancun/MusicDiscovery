package com.yc.music.mapper;

import com.yc.music.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    void insertUser(UserVo userVo);

}
