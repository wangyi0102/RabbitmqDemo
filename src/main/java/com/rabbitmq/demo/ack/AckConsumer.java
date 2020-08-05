package com.rabbitmq.demo.ack;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.demo.client.MyConsumer;
import com.rabbitmq.demo.common.RabbitmqConnectionFactory;

/**
 * @author 核桃(wy)
 * @date 2020/8/4 17:10
 * @description ack消费者
 **/
public class AckConsumer {

    private static final String QUEUE = "test.ack.queue";
    private static final String EXCHANG_NAME = "test.ack.exchang";
    private static final String ROUTING_KEY = "test.ack.*";

    public static void main(String args[]) throws Exception {
        // 1、获取连接工厂
        ConnectionFactory connectionFactory = RabbitmqConnectionFactory.getConnectionFactory();
        // 2、创建连接
        Connection connection = connectionFactory.newConnection();
        // 3、创建通道
        Channel channel = connection.createChannel();
        // 4、创建队列、交换机，并绑定
        channel.exchangeDeclare(EXCHANG_NAME, BuiltinExchangeType.TOPIC, true);
        channel.queueDeclare(QUEUE, true, false, false, null);
        channel.queueBind(QUEUE, EXCHANG_NAME ,ROUTING_KEY);
        // 5、创建消费者
        MyConsumer consumer = new MyConsumer(channel);
        // autoAck 必须设置为 false （手动签收）
        channel.basicConsume(QUEUE, false, consumer);
    }

}
