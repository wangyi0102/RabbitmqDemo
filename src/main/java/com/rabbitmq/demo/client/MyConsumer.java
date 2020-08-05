package com.rabbitmq.demo.client;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * @author 核桃(wy)
 * @date 2020/8/4 10:54
 * @description 自定义消费者
 **/
public class MyConsumer extends DefaultConsumer {

    private Channel channel;

    public MyConsumer(Channel channel) {
        super(channel);
        this.channel = channel;
    }

    /**
     * 重写父类方法
     * @param consumerTag
     * @param envelope
     * @param properties
     * @param body
     */
    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body){
        System.out.println("--------------- MyConsumer Message ----------------");
        System.out.println("message body :" + new String(body));
        try {
            //Thread.sleep(5000);
            // 手动确认消息,false：不批量签收
            //channel.basicAck(envelope.getDeliveryTag(), false);
            // 未签收，第三参数：是否重回队列
            channel.basicNack(envelope.getDeliveryTag(), false, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
