这里演示的是在没有Exchang交换机的情况下投递消息到队列：
在没有生产者没有指定Exchang的情况下，rabbitmq会默认有一个AMQP default交换机,
这种情况投递消息到队列的话，需要保证队列名称和路由key完全相同，就可以投递到队列里面。