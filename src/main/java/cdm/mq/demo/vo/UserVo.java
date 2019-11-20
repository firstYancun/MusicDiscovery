package cdm.mq.demo.vo;

import com.carrefour.cn.cdm.common.vos.BaseVo;
import lombok.Data;

@Data
public class UserVo extends BaseVo {

    private Long userId;

    private String userName;

    private String password;

    private Integer gender;

    private String address;

}
