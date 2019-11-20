package cdm.mq.demo.mq;

import cdm.mq.demo.util.SpringContextUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.carrefour.cn.cdm.common.vos.BaseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Map;

import static cdm.mq.demo.config.ActiveMQConfig.*;

@Slf4j
@Component
public class ActiveMQCustomer {

    /**
     * 点对点消费用户队列 并利用反射执行service方法
     *
     * @param message 要处理的消息
     */
    @JmsListener(destination = QUEUE_USER, containerFactory = QUEUE_LISTENER_CONTAINER_FACTORY)
    public <T extends BaseVo>void handlerQueue(String message) {
        log.info("MQCustomer start to handler queue message:" + message);
        try {
            JSONObject jsonObject = JSONObject.parseObject(message);
            String className = jsonObject.getString("className");
            String methodName = jsonObject.getString("methodName");
            String methodClassStr = jsonObject.getString("methodClass");
            Class methodClass = Class.forName(methodClassStr);
            JSONObject voObject = (JSONObject) jsonObject.get("vo");
            T insertData = (T)JSON.toJavaObject(voObject,methodClass);
            Method mh = ReflectionUtils.findMethod(SpringContextUtil.getBean(className).getClass(),
                    methodName, methodClass);
            ReflectionUtils.invokeMethod(mh, SpringContextUtil.getBean(className), insertData);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 1对多消费用户队列
     *
     * @param message 要处理的消息
     */
    @JmsListener(destination = TOPIC_USER, containerFactory = TOPIC_LISTENER_CONTAINER_FACTORY)
    public void handlerTopic(String message) {
        log.info("MQCustomer start to handler topic message,{}.", message);
    }


}
