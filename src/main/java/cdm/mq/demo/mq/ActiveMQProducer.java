package cdm.mq.demo.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;
import javax.jms.Queue;
import javax.jms.Topic;

@Slf4j
@Service("producer")
public class ActiveMQProducer {


    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Autowired
    private Topic topic;

    @Autowired
    private Queue queue;


    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendMessage(Destination destination, final String message) {
        jmsTemplate.convertAndSend(destination, message);
    }

    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendTopicMessage(final String message) {
        log.info("发送topic MQ消息开始。");
        jmsTemplate.convertAndSend(this.topic, message);
    }


    // 发送消息，destination是发送到的队列，message是待发送的消息
    public void sendQueueMessage(final String message) {
        log.info("发送queue MQ消息开始。");
        jmsTemplate.convertAndSend(this.queue, message);
    }
}
