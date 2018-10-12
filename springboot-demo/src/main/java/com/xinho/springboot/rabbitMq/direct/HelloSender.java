package com.xinho.springboot.rabbitMq.direct;

import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/2114:12
 */
@Component
public class HelloSender  {


    @Resource
    private RabbitTemplate rabbitTemplate;


    public void send(String params){
        rabbitTemplate.convertAndSend("queue","hello rabbit"+params);
    }
    public void send1(){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("生产correlationId："+correlationId);
        rabbitTemplate.convertAndSend("exchange","topic.message","hello topic",correlationId);
    }
    public void send2(){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("fanoutExchange","1111","hello fanout",correlationId);
    }



    public void sendConfim(){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("生产correlationId："+correlationId);
        rabbitTemplate.convertAndSend("exchange","topic.messagesConfim1","hello topic",correlationId);
    }

    public void sendDeadLetter(){
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
      //  声明消息处理器  这个对消息进行处理  可以设置一些参数   对消息进行一些定制化处理   我们这里  来设置消息的编码  以及消息的过期时间  因为在.net 以及其他版本过期时间不一致   这里的时间毫秒值 为字符串
        MessagePostProcessor messagePostProcessor = message -> {
            MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
            messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
            messageProperties.setExpiration("10000");
            return message;
        };
//         向DL_QUEUE 发送消息  10*1000毫秒后过期 形成死信
        rabbitTemplate.convertAndSend("DL_EXCHANGE", "DL_KEY", "deadLatter", messagePostProcessor, correlationId);
    }

}
