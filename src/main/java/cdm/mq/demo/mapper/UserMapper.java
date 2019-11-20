package cdm.mq.demo.mapper;

import cdm.mq.demo.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserMapper {

    void insertUser(UserVo userVo);

}
