package com.xinho.springboot.customer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/2114:43
 */
@Component
public class HelloReceive {


    @Value("${server.port}")
    String port;

    /***********driect*******/
    @RabbitListener(queues="queue")    //监听器监听指定的Queue
    public void process(String str) {
        System.out.println("消费者:"+port+"----Receive:"+str);
    }

    /***************topic**************/
    @RabbitListener(queues="topic.message")    //监听器监听指定的Queue
    public void process1(String str) {




        System.out.println("messages:"+str);


    }
    @RabbitListener(queues="topic.messages")    //监听器监听指定的Queue
    public void process2(Message message, Channel channel)throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), true);
        System.out.println("messages:"+message.toString());



    }

    /*********fanout**********/
    @RabbitListener(queues="fanout.A")
    public void processA(String message) {
        System.out.println("ReceiveA:"+message);
    }
    @RabbitListener(queues="fanout.B")
    public void processB(String message) {
        System.out.println("ReceiveB:"+message);
    }
    @RabbitListener(queues="fanout.C")
    public void processC(String message) {
        System.out.println("ReceiveC:"+message);
    }


//    @RabbitListener(queues="topic.messagesConfim2")
//    public void process222(Message message) {
//        System.out.println("messagesConfim2:"+message.getBody());
//    }

//    @RabbitListener(queues="topic.messagesConfim")
//    public void proces(Message message) {
//        System.out.println("messagesConfim:"+message.getBody());
//    }

    @RabbitListener(queues="topic.messagesConfim1")
    public void process111(Message message) {
        System.out.println("messagesConfim1:"+message.getBody());
    }
//
//    @RabbitListener(queues="REDIRECT_QUEUE")
//    public void processD(Message message, Channel channel) throws  IOException{
//        channel.basicAck(message.getMessageProperties().getDeliveryTag(),true);
//        System.out.println("REDIRECT_QUEUE:"+message.getBody());
//    }
}
