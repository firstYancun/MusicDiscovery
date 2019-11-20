package cdm.mq.demo.vo;

import com.carrefour.cn.cdm.common.vos.BaseVo;
import lombok.Data;

@Data
public class MQEventVo<T extends BaseVo> {

    //类名
    private String className;

    //方法名
    private String methodName;

    private Class<T> methodClass;

    //vo入参
    private T vo;

    public MQEventVo(String className,
                     String methodName,
                     T vo,
                     Class<T> methodClass) {
        this.className = className;
        this.methodName = methodName;
        this.vo = vo;
        this.methodClass = methodClass;
    }


}
