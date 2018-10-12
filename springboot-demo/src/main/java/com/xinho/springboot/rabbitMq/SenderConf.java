package com.xinho.springboot.rabbitMq;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * @author lhf
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/5/2114:00
 */
@Configuration
public class SenderConf {

    private static final String DEAD_LETTER_QUEUE_KEY="x-dead-letter-exchange";

    private static final String DEAD_LETTER_ROUTING_KEY="x-dead-letter-routing-key";

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 定制化amqp模版      可根据需要定制多个
     *
     *
     * 此处为模版类定义 Jackson消息转换器
     * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调   即消息发送到exchange  ack
     * ReturnCallback接口用于实现消息发送到RabbitMQ 交换器，但无相应队列与交换器绑定时的回调  即消息发送不到任何一个队列中  ack
     *
     * @return the amqp template
     */
//   @Primary
    @Bean
    public AmqpTemplate amqpTemplate() {
        Logger log = LoggerFactory.getLogger(RabbitTemplate.class);
        //使用jackson 消息转换器
//        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
//        rabbitTemplate.setEncoding("UTF-8");
        //开启returncallback     需要 配置    publisher-returns: true
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
            log.info("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
        });
        //消息确认   需要配置   publisher-returns: true
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if (ack) {
                log.info("消息发送到exchange成功,id: {}", correlationData.getId());
            } else {
                log.info("消息发送到exchange失败,原因: {}", cause);
            }
        });
        return rabbitTemplate;
    }

    /**
     ***********************direct模式**************************
     * Direct是RabbitMQ默认的交换机模式,也是最简单的模式.即创建消息队列的时候,
     * 指定一个BindingKey.当发送者发送消息的时候,指定对应的Key.
     * 当Key和消息队列的BindingKey一致的时候,消息将会被发送到该消息队列中
     */
    @Bean
    public Queue queue(){

        return  new Queue("queue");
    }

    /**
     *********************Topic转发模式********************
     * topic转发信息主要是依据通配符,队列和交换机的绑定主要是依据一种模式(通配符+字符串),
     * 而当发送消息的时候,只有指定的Key和该模式相匹配的时候,消息才会被发送到该消息队列中.
     */

    @Bean(name="message")
    public Queue queueMessage() {
        return QueueBuilder.durable("topic.message").build();
    }
    @Bean(name="messages")
    public Queue queueMessages() {
        return QueueBuilder.durable("topic.messages").build();
    }
    @Bean(name="messagesConfim1")
    public Queue queueMessagesConfim() {
        return QueueBuilder.durable("topic.messagesConfim1").build();
    }

    @Bean
    public TopicExchange exchange() {
        return (TopicExchange)ExchangeBuilder.topicExchange("exchange").durable(true).build();
    }
    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");
    }

    @Bean
    Binding bindingExchangeMessagesConfim(@Qualifier("messagesConfim1") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.messagesConfim1");//*表示一个词,#表示零个或多个词
    }
    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.messages");//*表示一个词,#表示零个或多个词
    }

    /**
     * ************************Fanout模式********************
     * Fanout是路由广播的形式,将会把消息发给绑定它的全部队列,即便设置了key,也会被忽略.
     */

    @Bean(name="Amessage")
    public Queue AMessage() {
        return QueueBuilder.durable("fanout.A").build();
    }


    @Bean(name="Bmessage")
    public Queue BMessage() {
        return QueueBuilder.durable("fanout.B").build();
    }

    @Bean(name="Cmessage")
    public Queue CMessage() {
        return QueueBuilder.durable("fanout.C").build();
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return (FanoutExchange)ExchangeBuilder.fanoutExchange("fanoutExchange").durable(true).build();//配置广播路由器
    }

    @Bean
    Binding bindingExchangeA(@Qualifier("Amessage") Queue AMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(@Qualifier("Bmessage") Queue BMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BMessage).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(@Qualifier("Cmessage") Queue CMessage, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CMessage).to(fanoutExchange);
    }











    /**********************死信队列***************************/
    /**
     * 死信队列跟交换机类型没有关系 不一定为directExchange  不影响该类型交换机的特性.
     *
     * @return the exchange
     */
    @Bean("deadLetterExchange")
    public Exchange deadLetterExchange() {
        return ExchangeBuilder.directExchange("DL_EXCHANGE").durable(true).build();
    }
    /**
     * 声明一个死信队列.
     * x-dead-letter-exchange   对应  死信交换机
     * x-dead-letter-routing-key  对应 死信队列
     *
     * @return the queue
     */
    @Bean("deadLetterQueue")
    public Queue deadLetterQueue() {
        Map<String, Object> args = new HashMap<>(2);
//       x-dead-letter-exchange    声明  死信交换机
        args.put(DEAD_LETTER_QUEUE_KEY, "DL_EXCHANGE");
//       x-dead-letter-routing-key    声明 死信路由键
        args.put(DEAD_LETTER_ROUTING_KEY, "KEY_R");
        return QueueBuilder.durable("DL_QUEUE").withArguments(args).build();
    }

    /**
     * 定义死信队列转发队列.
     *
     * @return the queue
     */
    @Bean("redirectQueue")
    public Queue redirectQueue() {
        return QueueBuilder.durable("REDIRECT_QUEUE").build();
    }

    /**
     * 死信路由通过 DL_KEY 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding deadLetterBinding() {
        return new Binding("DL_QUEUE", Binding.DestinationType.QUEUE, "DL_EXCHANGE", "DL_KEY", null);

    }

    /**
     * 死信路由通过 KEY_R 绑定键绑定到死信队列上.
     *
     * @return the binding
     */
    @Bean
    public Binding redirectBinding() {
        return new Binding("REDIRECT_QUEUE", Binding.DestinationType.QUEUE, "DL_EXCHANGE", "KEY_R", null);
    }
}
